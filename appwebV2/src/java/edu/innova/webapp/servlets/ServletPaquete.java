package edu.innova.webapp.servlets;

import edu.innova.webapp.dtos.CategoriaDTO;
import edu.innova.webapp.dtos.EspectaculoDTO;
import edu.innova.webapp.dtos.InformacionEspectaculoDTO;
import edu.innova.webapp.dtos.InformacionPaqueteDTO;
import edu.innova.webapp.dtos.PaqueteDTO;
import edu.innova.webapp.dtos.PlataformaDTO;
import edu.innova.webapp.dtos.UsuarioDTO;
import edu.innova.webapp.helpers.Constantes;
import edu.innova.webapp.helpers.HelperFechas;
import edu.innova.webapp.helpers.HelperImagenes;
import edu.innova.webapp.helpers.HelperImagenes.CarpetaDestinoImagenes;
import edu.innova.webapp.logica.servicios.ServicioEspectaculos;
import edu.innova.webapp.logica.servicios.ServicioPaquetes;
import edu.innova.webapp.logica.servicios.ServicioUsuarios;
import edu.innova.webapp.logica.servicios.impl.ServicioEspectaculosAppSwingImpl;
import edu.innova.webapp.logica.servicios.impl.ServicioMenu;
import edu.innova.webapp.logica.servicios.impl.ServicioPaquetesAppSwingImpl;
import edu.innova.webapp.logica.servicios.impl.ServicioUsuariosAppSwingImpl;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

@WebServlet(name = "paquete", urlPatterns = {"/paquete"})
@MultipartConfig
public class ServletPaquete extends HttpServlet {

    private static final HelperImagenes helperImagenes = HelperImagenes.getInstance();
    private static final ServicioUsuarios servicioUsuarios = ServicioUsuariosAppSwingImpl.getInstance();
    private static final ServicioEspectaculos servicioEspectaculos = ServicioEspectaculosAppSwingImpl.getInstance();
    private static final ServicioPaquetes servicioPaquetes = ServicioPaquetesAppSwingImpl.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();
        UsuarioDTO usuarioLogueado = (UsuarioDTO) session.getAttribute(Constantes.USUARIO);

        String operacion = req.getParameter("operacion");

        if ("altapaquete".equalsIgnoreCase(operacion)) {
            if (usuarioLogueado == null) {
                req.getRequestDispatcher("usuario/miperfil.jsp").forward(req, resp);
                return;
            }
            Long idArtista = usuarioLogueado.getId();
            String nombre = req.getParameter("nombre");
            String descripcion = req.getParameter("descripcion");
            BigDecimal descuento = new BigDecimal(req.getParameter("descuento"));
            String fecha = req.getParameter(Constantes.FECHA);
            Date fechaInicio = HelperFechas.stringToDate(req.getParameter("fechaInicio"), "yyyy-MM-dd");
            Date fechaFin = HelperFechas.stringToDate(req.getParameter("fechaFin"), "yyyy-MM-dd");

            String imagen = null;

            if (req.getPart("imagen") != null) {
                Part part = req.getPart("imagen");
                imagen = helperImagenes.crearNombreArchivo(part, nombre);
                if (null != imagen) {
                    if (!helperImagenes.guardarImagen(part, CarpetaDestinoImagenes.PAQUETES, imagen)) {
                        imagen = null;
                    }
                }
            }

            try {
                PaqueteDTO paquete = new PaqueteDTO(idArtista, nombre, descripcion, fechaInicio, fechaFin, descuento, imagen);
                servicioPaquetes.altaPaquete(paquete, usuarioLogueado.getId());
                req.getRequestDispatcher("paquete/mispaquetes.jsp").forward(req, resp);
                return;
            } catch (Exception e) {
                req.setAttribute("error", e.getMessage());
                req.getRequestDispatcher("paquete/alta.jsp").forward(req, resp);
                return;
            }
        } else if ("espectaculoapaquete".equalsIgnoreCase(operacion)) {
            Long idPaquete = Long.valueOf(req.getParameter("idPaquete"));
            Long idEspectaculo = Long.valueOf(req.getParameter("espectaculonuevo"));
            try {
                servicioPaquetes.altaEspectaculoEnPaquete(idEspectaculo, idPaquete);
                req.getRequestDispatcher("paquete/mispaquetes.jsp").forward(req, resp);
                return;
            } catch (Exception e) {
                req.setAttribute("error", e.getMessage());
                req.getRequestDispatcher("paquete/agregarespectaculo.jsp").forward(req, resp);
                return;
            }
        }

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        HttpSession session = req.getSession();
        UsuarioDTO usuarioLogueado = (UsuarioDTO) session.getAttribute(Constantes.USUARIO);
        
        String operacion = req.getParameter("operacion");

        if ("adquirirpaquete".equalsIgnoreCase(operacion)) {
            if (req.getParameter("idPaquete") == null || usuarioLogueado == null) {
                req.getRequestDispatcher("paquete/index.jsp").forward(req, resp);
            } else {
                Long idUsuario = usuarioLogueado.getId();
                Long idPaquete = Long.valueOf(req.getParameter("idPaquete"));
                try {
                    servicioPaquetes.altaUsuarioEnPaquete(idUsuario, idPaquete);
                    req.setAttribute(Constantes.MENSAJE, "El Paquete se agrego correctamente");
                    req.getRequestDispatcher("paquete/index.jsp").forward(req, resp);
                } catch (Exception e) {
                    req.setAttribute(Constantes.ERROR, e.getMessage());
                    req.getRequestDispatcher("paquete/index.jsp").forward(req, resp);
                    return;
                }
            }
        }
    }

    public static InformacionEspectaculoDTO getEspectaculoPorId(Long idEspectaculo) {
        InformacionEspectaculoDTO informacionEspectaculo = new InformacionEspectaculoDTO();
        EspectaculoDTO espectaculo = servicioEspectaculos.getEspectaculoPorId(idEspectaculo);
        UsuarioDTO usuario = servicioUsuarios.getUsuarioPorId(idEspectaculo);

        CategoriaDTO categoria
                = ServicioMenu.getCategoriasDTO()
                        .stream()
                        .filter(categoriaDTO -> categoriaDTO.getId() == espectaculo.getIdCategoria())
                        .findFirst()
                        .get();

        PlataformaDTO plataforma
                = ServicioMenu.getPlataformas()
                        .stream()
                        .filter(plataformaDTO -> plataformaDTO.getId() == espectaculo.getIdPlataforma())
                        .findFirst()
                        .get();

        informacionEspectaculo.setEspectaculo(espectaculo);
        informacionEspectaculo.setUsuario(usuario);
        informacionEspectaculo.setCategoria(categoria);
        informacionEspectaculo.setPlataforma(plataforma);
        return informacionEspectaculo;
    }

    public static List<PaqueteDTO> getPaquetesPorIdUsuario(Long idArtista) {
        return servicioPaquetes.getPaquetesPorIdArtista(idArtista);
    }
    
    public static List<PaqueteDTO> getPaquetesCompradosPorIdUsuario(Long idUsuario) {
        return servicioPaquetes.getPaquetesCompradosPorIdUsuario(idUsuario);
    }

    public static InformacionPaqueteDTO getPaquetePorId(Long idPaquete) {
        InformacionPaqueteDTO informacionPaquete = new InformacionPaqueteDTO();
        PaqueteDTO paquete = servicioPaquetes.getPaquetePorId(idPaquete);
        String categoriasDelPaquete = "";
        String plataformasDelPaquete = "";

        if (paquete.getEspectaculos() != null && paquete.getEspectaculos().size() > 0) {
            List<CategoriaDTO> categorias = ServicioMenu.getCategoriasDTO();
            List<PlataformaDTO> plataformas = ServicioMenu.getPlataformas();

            categoriasDelPaquete = paquete.getEspectaculos()
                    .stream()
                    .map(espectaculo -> {
                        return categorias
                                .stream()
                                .filter(c -> Objects.equals(c.getId(), espectaculo.getIdCategoria())).findFirst().get().getNombre();
                    }).collect(Collectors.toSet()).stream()
                    .collect(Collectors.joining(", "));
            plataformasDelPaquete = paquete.getEspectaculos()
                    .stream()
                    .map(espectaculo -> {
                        return plataformas
                                .stream()
                                .filter(c -> Objects.equals(c.getId(), espectaculo.getIdPlataforma())).findFirst().get().getNombre();
                    })
                    .collect(Collectors.toSet()).stream().collect(Collectors.joining(", "));
        }

        informacionPaquete.setPaquete(paquete);
        informacionPaquete.setCategorias(categoriasDelPaquete);
        informacionPaquete.setPlataformas(plataformasDelPaquete);
        return informacionPaquete;
    }

    public static List<EspectaculoDTO> getEspectaculosNoIncluidosEnPaquetePorId(Long idUsuario, Long idPaquete) {
        return servicioPaquetes.getEspectaculosNoIncluidosEnPaquete(idUsuario, idPaquete);
    }

    public static List<PaqueteDTO> getTodosLosPaquetes() {
        return servicioPaquetes.getTodosLosPaquetes();
    }
}
