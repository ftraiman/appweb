package edu.innova.webapp.servlets;

import edu.innova.webapp.dtos.EspectaculoDTO;
import edu.innova.webapp.dtos.FuncionDTO;
import edu.innova.webapp.dtos.InformacionCanjePaqueteDTO;
import edu.innova.webapp.dtos.InformacionCanjeTresPorUnoDTO;
import edu.innova.webapp.dtos.InformacionFuncionDTO;
import edu.innova.webapp.dtos.UsuarioDTO;
import edu.innova.webapp.helpers.Constantes;
import edu.innova.webapp.helpers.HelperFechas;
import edu.innova.webapp.helpers.HelperImagenes;
import edu.innova.webapp.logica.servicios.ServicioEspectaculos;
import edu.innova.webapp.logica.servicios.ServicioPaquetes;
import edu.innova.webapp.logica.servicios.ServicioUsuarios;
import edu.innova.webapp.logica.servicios.impl.ServicioEspectaculosAppSwingImpl;
import edu.innova.webapp.logica.servicios.impl.ServicioPaquetesAppSwingImpl;
import edu.innova.webapp.logica.servicios.impl.ServicioUsuariosAppSwingImpl;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

@WebServlet(name = "funcion", urlPatterns = {"/funcion"})
@MultipartConfig
public class ServletFuncion extends HttpServlet {

    private final static HelperImagenes helperImagenes = HelperImagenes.getInstance();
    private final static ServicioUsuarios servicioUsuarios = ServicioUsuariosAppSwingImpl.getInstance();
    private final static ServicioEspectaculos servicioEspectaculos = ServicioEspectaculosAppSwingImpl.getInstance();
    private final static ServicioPaquetes servicioPaquetes = ServicioPaquetesAppSwingImpl.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();
        UsuarioDTO usuarioLogueado = (UsuarioDTO) session.getAttribute(Constantes.USUARIO);

        Long idEspectaculo = Long.valueOf(req.getParameter(Constantes.ID_ESPECTACULO));
        String nombre = req.getParameter(Constantes.NOMBRE);
        String fecha = req.getParameter(Constantes.FECHA);
        Date fechaInicio = HelperFechas.stringToDate(fecha, "yyyy-MM-dd'T'HH:mm");

        String[] invitados = req.getParameterValues(Constantes.INVITADOS);
        List<UsuarioDTO> usuariosInvitados = Stream.of(invitados)
                .map(num -> Long.valueOf(num))
                .map(id -> {
                    UsuarioDTO usuario = new UsuarioDTO();
                    usuario.setId(id);
                    return usuario;
                })
                .collect(Collectors.toList());

        if (usuariosInvitados.stream().anyMatch(usuario -> usuario.getId() == 0)) {
            usuariosInvitados = new ArrayList<>();
        }

        String imagen = null;

        if (req.getPart("imagen") != null) {
            Part part = req.getPart("imagen");
            imagen = helperImagenes.crearNombreArchivo(part, nombre);
            if (null != imagen) {
                if (!helperImagenes.guardarImagen(part, HelperImagenes.CarpetaDestinoImagenes.FUNCIONES, imagen)) {
                    imagen = null;
                }
            }
        }

        FuncionDTO nuevaFuncion = new FuncionDTO(nombre, idEspectaculo, fechaInicio, new Date(), usuariosInvitados, imagen);

        try {
            servicioEspectaculos.altaFuncion(nuevaFuncion);
            req.getRequestDispatcher(String.format("espectaculo/detalle.jsp?idEspectaculo=%s", idEspectaculo)).forward(req, resp);
        } catch (Exception e) {
            req.setAttribute(Constantes.ERROR, e.getMessage());
            req.getRequestDispatcher("funcion/alta.jsp").forward(req, resp);
            return;
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    public static List<UsuarioDTO> getTodosLosArtisas(Long idArtistaOrganizador) {
        List<UsuarioDTO> artistas = servicioUsuarios.getTodosLosUsuarios()
                .stream()
                .filter(usuario -> Constantes.ARTISTA.equalsIgnoreCase(usuario.getTipo()))
                .collect(Collectors.toList());
        UsuarioDTO artistaOrganizador = new UsuarioDTO();
        artistaOrganizador.setId(idArtistaOrganizador);
        artistas.remove(artistaOrganizador);
        return artistas;
    }

    public static InformacionFuncionDTO getInformacionFuncion(Long idFuncion, HttpServletRequest req) {

        HttpSession session = req.getSession();
        UsuarioDTO usuarioLogueado = (UsuarioDTO) session.getAttribute(Constantes.USUARIO);

        InformacionFuncionDTO informacionFuncion = new InformacionFuncionDTO();
        FuncionDTO funcion = servicioEspectaculos.getFuncionPorId(idFuncion);
        EspectaculoDTO espectaculo = servicioEspectaculos.getEspectaculoPorId(funcion.getIdEspectaculo());
        UsuarioDTO artista = servicioUsuarios.getUsuarioPorId(espectaculo.getIdArtista());
        Boolean isFuncionCompleta = servicioEspectaculos.isFuncionCompleta(idFuncion);
        Boolean isUsuarioLogueado = usuarioLogueado != null;
        Boolean usuarioRegistradoEnFuncion = false;
        Boolean isFuncionFavorita = false;

        if (isUsuarioLogueado) {
            if (!isFuncionCompleta) {
                usuarioRegistradoEnFuncion = servicioEspectaculos.isUsuarioRegistradoEnFuncion(idFuncion, usuarioLogueado.getId());
            }
            isFuncionFavorita = servicioEspectaculos.isFuncionFavoritaDelUsuario(idFuncion, usuarioLogueado.getId());
        }

        informacionFuncion.setFuncion(funcion);
        informacionFuncion.setEspectaculo(espectaculo);
        informacionFuncion.setArtista(artista);
        informacionFuncion.setIsUsuarioLogueado(usuarioLogueado != null);
        informacionFuncion.setIsUsuarioRegistradoEnFuncion(usuarioRegistradoEnFuncion);
        informacionFuncion.setIsFuncionCompleta(isFuncionCompleta);
        informacionFuncion.setIsFuncionFavorita(isFuncionFavorita);
        return informacionFuncion;
    }

    public static InformacionCanjeTresPorUnoDTO getInfoCanjeTresPorUno(Long idUsuario) {
        return servicioEspectaculos.getInfoCanjeTresPorUno(idUsuario);
    }

    public static InformacionCanjePaqueteDTO getInfoCanjePaquete(Long idUsuario, Long idFuncion) {
        return servicioPaquetes.getInfoCanjePaquete(idUsuario, idFuncion);
    }
    
    public static List<UsuarioDTO> getUsuariosEnFuncion(Long idFuncion) {
        return servicioUsuarios.getUsuariosRegistradosALaFuncion(idFuncion);
    }
    
    public static List<UsuarioDTO> getGanadoresDelSorteo(Long idFuncion, Integer premios) {
        return servicioUsuarios.getGanadoresDelSorteo(idFuncion, premios);
    }
    
    public static void registrarGanadoresDelSorteo(Long idFuncion, List<Long>idUsuarios, String premios) {
        servicioUsuarios.registrarGanadores(idFuncion, idUsuarios, premios);
    }
}
