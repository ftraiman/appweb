package edu.innova.webapp.servlets;

import edu.innova.webapp.dtos.CategoriaDTO;
import edu.innova.webapp.dtos.EspectaculoDTO;
import edu.innova.webapp.dtos.InformacionEspectaculoDTO;
import edu.innova.webapp.dtos.PaqueteDTO;
import edu.innova.webapp.dtos.PlataformaDTO;
import edu.innova.webapp.dtos.UsuarioDTO;
import edu.innova.webapp.helpers.Constantes;
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
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

@WebServlet(name = "altaespectaculo", urlPatterns = {"/altaespectaculo"})
@MultipartConfig
public class ServletAltaEspectaculo extends HttpServlet {

    private static final HelperImagenes helperImagenes = HelperImagenes.getInstance();
    private static final ServicioUsuarios servicioUsuarios = ServicioUsuariosAppSwingImpl.getInstance();
    private static final ServicioEspectaculos servicioEspectaculos = ServicioEspectaculosAppSwingImpl.getInstance();
    private static final ServicioPaquetes servicioPaquetes = ServicioPaquetesAppSwingImpl.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();
        UsuarioDTO usuarioLogueado = (UsuarioDTO) session.getAttribute(Constantes.USUARIO);

        Long idCategoria = Long.valueOf(req.getParameter("categoria"));
        Long idPlataforma = Long.valueOf(req.getParameter("plataforma"));
        String nombre = req.getParameter("nombre");
        String descripcion = req.getParameter("descripcion");
        String url = req.getParameter("url");

        Integer duracion = Integer.valueOf(req.getParameter("duracion"));
        Integer espectadoresMinimos = Integer.valueOf(req.getParameter("espectadoresMinimos"));
        Integer espectadoresMaximos = Integer.valueOf(req.getParameter("espectadoresMaximos"));
        BigDecimal costo = new BigDecimal(req.getParameter("costo"));

        String imagen = null;

        if (req.getPart("imagen") != null) {
            Part part = req.getPart("imagen");
            imagen = helperImagenes.crearNombreArchivo(part, nombre);
            if (null != imagen) {
                if (!helperImagenes.guardarImagen(part, CarpetaDestinoImagenes.ESPECTACULOS, imagen)) {
                    imagen = null;
                }
            }
        }

        try {
            // TODO validaciones

            EspectaculoDTO espectaculo = new EspectaculoDTO();
            espectaculo.setIdArtista(usuarioLogueado.getId());
            espectaculo.setIdPlataforma(idPlataforma);
            espectaculo.setIdCategoria(idCategoria);

            espectaculo.setNombre(nombre);
            espectaculo.setDescripcion(descripcion);
            espectaculo.setDuracion(duracion);
            espectaculo.setCosto(costo);
            espectaculo.setEspectadoresMaximos(espectadoresMaximos);
            espectaculo.setEspectadoresMinimos(espectadoresMinimos);
            espectaculo.setEstado(Constantes.INGRESADO);
            espectaculo.setFechaRegistro(new Date());
            espectaculo.setUrl(url);
            espectaculo.setImagen(imagen);
            servicioEspectaculos.altaEspectaculo(espectaculo);

            req.getRequestDispatcher("usuario/miperfil.jsp").forward(req, resp);
            return;

        } catch (Exception e) {
            req.setAttribute("error", e.getMessage());
            req.getRequestDispatcher("espectaculo/alta.jsp").forward(req, resp);
            return;
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    public static InformacionEspectaculoDTO getEspectaculoPorId(Long idEspectaculo) {
        InformacionEspectaculoDTO informacionEspectaculo = new InformacionEspectaculoDTO();
        EspectaculoDTO espectaculo = servicioEspectaculos.getEspectaculoPorId(idEspectaculo);
        UsuarioDTO usuario = servicioUsuarios.getUsuarioPorId(espectaculo.getIdArtista());

        CategoriaDTO categoria
                = ServicioMenu.getCategoriasDTO()
                        .stream()
                        .filter(categoriaDTO -> Objects.equals(categoriaDTO.getId(), espectaculo.getIdCategoria()))
                        .findFirst()
                        .get();

        PlataformaDTO plataforma
                = ServicioMenu.getPlataformas()
                        .stream()
                        .filter(plataformaDTO -> Objects.equals(plataformaDTO.getId(), espectaculo.getIdPlataforma()))
                        .findFirst()
                        .get();

        List<PaqueteDTO> paquetes = servicioPaquetes.getPaquetesPorIdEspectaculo(idEspectaculo);
        
        informacionEspectaculo.setEspectaculo(espectaculo);
        informacionEspectaculo.setUsuario(usuario);
        informacionEspectaculo.setCategoria(categoria);
        informacionEspectaculo.setPlataforma(plataforma);
        informacionEspectaculo.setPaquetes(paquetes);
        return informacionEspectaculo;
    }
    
    public static List<EspectaculoDTO> getEspectaculosPorPlataformaCategoria(Long idPlataforma, Long idCategoria) {
        return servicioEspectaculos.getEspectaculosPorCategoriaPlataforma(idPlataforma, idCategoria);
    }
}
