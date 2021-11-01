package edu.innova.webapp.servlets;

import edu.innova.webapp.dtos.UsuarioDTO;
import edu.innova.webapp.helpers.Constantes;
import edu.innova.webapp.helpers.HelperFechas;
import edu.innova.webapp.helpers.HelperImagenes;
import edu.innova.webapp.logica.servicios.ServicioSeguidores;
import edu.innova.webapp.logica.servicios.ServicioUsuarios;
import edu.innova.webapp.logica.servicios.impl.ServicioSeguidoresAppSwingImpl;
import edu.innova.webapp.logica.servicios.impl.ServicioUsuariosAppSwingImpl;
import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "modificarusuario", urlPatterns = {"/modificarusuario"})
@MultipartConfig
public class ServletModificarUsuario extends HttpServlet {

    private final static HelperImagenes helperImagenes = HelperImagenes.getInstance();
    private final static ServicioUsuarios servicioUsuarios = ServicioUsuariosAppSwingImpl.getInstance();
    private final static ServicioSeguidores servicioSeguidores = ServicioSeguidoresAppSwingImpl.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //TODO Manejar errores
        HttpSession session = req.getSession();
        UsuarioDTO usuarioSession = (UsuarioDTO) session.getAttribute(Constantes.USUARIO);

        String nombre = req.getParameter("nombre");
        String apellido = req.getParameter("apellido");
        Date fechaNacimiento = HelperFechas.stringToDate(req.getParameter("fechanacimiento"), "yyyy-MM-dd");
        String descripcion = req.getParameter("descripcion");
        String biografia = req.getParameter("biografia");
        String link = req.getParameter("link");

        UsuarioDTO usuarioParaModificar = new UsuarioDTO();
        usuarioParaModificar.setTipo(usuarioSession.getTipo());
        usuarioParaModificar.setNickname(usuarioSession.getNickname());
        usuarioParaModificar.setId(usuarioSession.getId());
        usuarioParaModificar.setNombre(nombre);
        usuarioParaModificar.setApellido(apellido);
        usuarioParaModificar.setFechaNacimiento(fechaNacimiento);
        usuarioParaModificar.setDescripcion(descripcion);
        usuarioParaModificar.setBiografia(biografia);
        usuarioParaModificar.setLinkUsuario(link);

        if (Constantes.ARTISTA.equalsIgnoreCase(usuarioSession.getTipo())) {
            //TODO validar que esten los necesarios
            if (!isLinkValido(link)) {
                req.setAttribute(Constantes.ERROR, "El parametro ingresado no es un Link");
                req.getRequestDispatcher("usuario/miperfil.jsp").forward(req, resp);
                return;
            }
        }

        servicioUsuarios.modificarUsuarioDTO(usuarioParaModificar);

        UsuarioDTO usuarioModificado = servicioUsuarios.getUsuarioPorId(usuarioParaModificar.getId());
        session.setAttribute(Constantes.USUARIO, usuarioModificado);

        req.setAttribute(Constantes.MENSAJE, "Modificacion hecha correctamente");
        RequestDispatcher view = req.getRequestDispatcher("usuario/miperfil.jsp");
        view.forward(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        UsuarioDTO usuarioSession = (UsuarioDTO) session.getAttribute(Constantes.USUARIO);
        if (req.getParameter("dejardeseguir") != null) {
            Long idUsuarioSeguidor = usuarioSession.getId();
            Long idUsuarioSeguido = Long.valueOf(req.getParameter("dejardeseguir"));
            servicioSeguidores.dejarDeSeguiUsuario(idUsuarioSeguidor, idUsuarioSeguido);
            RequestDispatcher view = req.getRequestDispatcher("usuario/miperfil.jsp");
            view.forward(req, resp);
            return;
        }

        if (req.getParameter("seguirusuario") != null) {
            Long idUsuarioSeguidor = usuarioSession.getId();
            Long idUsuarioSeguido = Long.valueOf(req.getParameter("seguirusuario"));
            servicioSeguidores.seguirUsuario(idUsuarioSeguidor, idUsuarioSeguido);
            RequestDispatcher view = req.getRequestDispatcher("usuario/miperfil.jsp");
            view.forward(req, resp);
            return;
        }

    }

    public static List<UsuarioDTO> getUsuariosSeguidos(HttpServletRequest req) {
        HttpSession session = req.getSession();
        UsuarioDTO usuarioSession = (UsuarioDTO) session.getAttribute(Constantes.USUARIO);
        return servicioSeguidores.getUsuariosQueSigue(usuarioSession.getId());
    }

    public static Boolean isLinkValido(String link) {
        try {
            (new URL(link)).openStream().close();
            return true;
        } catch (IOException e) {
            return false;
        }
    }

}
