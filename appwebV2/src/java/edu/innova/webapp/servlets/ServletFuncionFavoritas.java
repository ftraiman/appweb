package edu.innova.webapp.servlets;

import edu.innova.webapp.dtos.EspectaculoDTO;
import edu.innova.webapp.dtos.FuncionDTO;
import edu.innova.webapp.dtos.InformacionCanjePaqueteDTO;
import edu.innova.webapp.dtos.InformacionCanjeTresPorUnoDTO;
import edu.innova.webapp.dtos.InformacionFuncionDTO;
import edu.innova.webapp.dtos.UsuarioDTO;
import edu.innova.webapp.helpers.Constantes;
import edu.innova.webapp.helpers.HelperImagenes;
import edu.innova.webapp.logica.servicios.ServicioEspectaculos;
import edu.innova.webapp.logica.servicios.ServicioPaquetes;
import edu.innova.webapp.logica.servicios.ServicioUsuarios;
import edu.innova.webapp.logica.servicios.impl.ServicioEspectaculosAppSwingImpl;
import edu.innova.webapp.logica.servicios.impl.ServicioPaquetesAppSwingImpl;
import edu.innova.webapp.logica.servicios.impl.ServicioUsuariosAppSwingImpl;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "favoritas", urlPatterns = {"/favoritas"})
@MultipartConfig
public class ServletFuncionFavoritas extends HttpServlet {

    private final static HelperImagenes helperImagenes = HelperImagenes.getInstance();
    private final static ServicioUsuarios servicioUsuarios = ServicioUsuariosAppSwingImpl.getInstance();
    private final static ServicioEspectaculos servicioEspectaculos = ServicioEspectaculosAppSwingImpl.getInstance();
    private final static ServicioPaquetes servicioPaquetes = ServicioPaquetesAppSwingImpl.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();
        UsuarioDTO usuarioLogueado = (UsuarioDTO) session.getAttribute(Constantes.USUARIO);

        String operacion = req.getParameter("operacion");
        Long idFuncion = Long.valueOf(req.getParameter("idFuncion"));
        Long idUsuario = usuarioLogueado.getId();

        try {
            if ("alta".equalsIgnoreCase(operacion)) {
                servicioEspectaculos.altaFuncionFavorita(idFuncion, idUsuario);
                req.setAttribute(Constantes.MENSAJE, "Se agrego correctamente la funcion como favorita");
            } else if ("baja".equalsIgnoreCase(operacion)) {
                servicioEspectaculos.bajaFuncionFavorita(idFuncion, idUsuario);
                req.setAttribute(Constantes.MENSAJE, "La Funcion dejo de ser favorita");
            }
            req.getRequestDispatcher(String.format("funcion/detalle.jsp?idFuncion=%s", idFuncion)).forward(req, resp);
        } catch (Exception e) {
            req.setAttribute("error", e.getMessage());
            req.getRequestDispatcher("funcion/detalle.jsp").forward(req, resp);
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

        if (isUsuarioLogueado && !isFuncionCompleta) {
            usuarioRegistradoEnFuncion = servicioEspectaculos.isUsuarioRegistradoEnFuncion(idFuncion, usuarioLogueado.getId());
        }

        informacionFuncion.setFuncion(funcion);
        informacionFuncion.setEspectaculo(espectaculo);
        informacionFuncion.setArtista(artista);
        informacionFuncion.setIsUsuarioLogueado(usuarioLogueado != null);
        informacionFuncion.setIsUsuarioRegistradoEnFuncion(usuarioRegistradoEnFuncion);
        informacionFuncion.setIsFuncionCompleta(isFuncionCompleta);

        return informacionFuncion;
    }

    public static InformacionCanjeTresPorUnoDTO getInfoCanjeTresPorUno(Long idUsuario) {
        return servicioEspectaculos.getInfoCanjeTresPorUno(idUsuario);
    }

    public static InformacionCanjePaqueteDTO getInfoCanjePaquete(Long idUsuario, Long idFuncion) {
        return servicioPaquetes.getInfoCanjePaquete(idUsuario, idFuncion);
    }
}
