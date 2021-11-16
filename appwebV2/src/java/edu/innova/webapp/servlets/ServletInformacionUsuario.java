package edu.innova.webapp.servlets;

import edu.innova.webapp.dtos.EspectaculoDTO;
import edu.innova.webapp.dtos.EspectaculosDeUsuario;
import edu.innova.webapp.dtos.UsuarioDTO;
import edu.innova.webapp.helpers.Constantes;
import edu.innova.webapp.helpers.HelperImagenes;
import edu.innova.webapp.logica.servicios.ServicioEspectaculos;
import edu.innova.webapp.logica.servicios.ServicioSeguidores;
import edu.innova.webapp.logica.servicios.ServicioUsuarios;
import edu.innova.webapp.logica.servicios.impl.ServicioEspectaculosAppSwingImpl;
import edu.innova.webapp.logica.servicios.impl.ServicioSeguidoresAppSwingImpl;
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

@WebServlet(name = "informacionusuario", urlPatterns = {"/informacionusuario"})
@MultipartConfig
public class ServletInformacionUsuario extends HttpServlet {

    private final static HelperImagenes helperImagenes = HelperImagenes.getInstance();
    private final static ServicioUsuarios servicioUsuarios = ServicioUsuariosAppSwingImpl.getInstance();
    private final static ServicioSeguidores servicioSeguidores = ServicioSeguidoresAppSwingImpl.getInstance();
    private final static ServicioEspectaculos servicioEspectaculos = ServicioEspectaculosAppSwingImpl.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();
        UsuarioDTO usuarioSession = (UsuarioDTO) session.getAttribute(Constantes.USUARIO);

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Long idUsuario = Long.valueOf(req.getParameter("idUsuario"));
        UsuarioDTO usuario = servicioUsuarios.getUsuarioPorId(idUsuario);
        List<UsuarioDTO> usuariosQueSigue = servicioSeguidores.getUsuariosQueSigue(idUsuario);
        List<UsuarioDTO> usuariosQueLoSiguen = servicioSeguidores.getUsuariosQueLoSiguen(idUsuario);

        HttpSession session = req.getSession();
        UsuarioDTO usuarioSession = (UsuarioDTO) session.getAttribute(Constantes.USUARIO);
        Boolean puedeSeguirUsuario = null;

        if (usuarioSession != null && usuario.getId().compareTo(usuarioSession.getId()) != 0) {
            puedeSeguirUsuario = !servicioSeguidores.getUsuariosQueSigue(usuarioSession.getId()).contains(usuario);
        }

        List<EspectaculoDTO> espectaculos = servicioEspectaculos.getEspectaculosPorIdArtista(idUsuario);

        req.setAttribute(Constantes.USUARIO, usuario);
        req.setAttribute("usuariosQueSigue", usuariosQueSigue);
        req.setAttribute("usuariosQueLoSiguen", usuariosQueLoSiguen);
        req.setAttribute("puedeSeguirUsuario", puedeSeguirUsuario);

        req.setAttribute(Constantes.ESPECTACULOS_ACEPTADOS, espectaculos.stream().filter(espectaculo -> Constantes.ESPECTACULOS_ACEPTADOS.equalsIgnoreCase(espectaculo.getEstado())));
        req.setAttribute(Constantes.ESPECTACULOS_INGRESADOS, espectaculos.stream().filter(espectaculo -> Constantes.ESPECTACULOS_INGRESADOS.equalsIgnoreCase(espectaculo.getEstado())));
        req.setAttribute(Constantes.ESPECTACULOS_RECHAZADOS, espectaculos.stream().filter(espectaculo -> Constantes.ESPECTACULOS_RECHAZADOS.equalsIgnoreCase(espectaculo.getEstado())));

        req.getRequestDispatcher("usuario/perfil.jsp").forward(req, resp);

    }

    public static EspectaculosDeUsuario getEspectaculosDeUsuarioPorIdArtista(Long idArtista) {
        EspectaculosDeUsuario espectaculosDeUsuario = new EspectaculosDeUsuario();

        List<EspectaculoDTO> espectaculos = servicioEspectaculos.getEspectaculosPorIdArtista(idArtista);
        espectaculosDeUsuario.setEspectaculosIngresados(espectaculos.stream()
                .filter(espectaculo -> Constantes.ESPECTACULOS_INGRESADOS.equalsIgnoreCase(espectaculo.getEstado())).collect(Collectors.toList()));
        espectaculosDeUsuario.setEspectaculosAceptados(espectaculos.stream()
                .filter(espectaculo -> Constantes.ESPECTACULOS_ACEPTADOS.equalsIgnoreCase(espectaculo.getEstado())).collect(Collectors.toList()));
        espectaculosDeUsuario.setEspectaculosRechazados(espectaculos.stream()
                .filter(espectaculo -> Constantes.ESPECTACULOS_RECHAZADOS.equalsIgnoreCase(espectaculo.getEstado())).collect(Collectors.toList()));
        espectaculosDeUsuario.setEspectaculosFinalizados(espectaculos.stream()
                .filter(espectaculo -> Constantes.FINALIZADO.equalsIgnoreCase(espectaculo.getEstado())).collect(Collectors.toList()));
        
        return espectaculosDeUsuario;
    }
    
    public static List<EspectaculoDTO> getEspectaculosFavoritosDelUsuario(Long idUsuario) {
        return servicioEspectaculos.getEspectaculosFavoritosDeUsuario(idUsuario);
    }
}
