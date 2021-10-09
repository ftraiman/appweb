package edu.innova.webapp.servlets;

import edu.innova.webapp.dtos.UsuarioDTO;
import edu.innova.webapp.exceptions.InnovaModelException;
import edu.innova.webapp.helpers.HelperFechas;
import edu.innova.webapp.helpers.HelperImagenes;
import edu.innova.webapp.helpers.HelperImagenes.CarpetaDestinoImagenes;
import edu.innova.webapp.logica.servicios.ServicioUsuarios;
import edu.innova.webapp.logica.servicios.impl.ServicioUsuariosAppWebImpl;
import java.io.IOException;
import java.util.Date;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@WebServlet(name = "altausuario", urlPatterns = {"/altausuario"})
@MultipartConfig
public class ServletAltaUsuario extends HttpServlet {

    HelperImagenes helperImagenes = HelperImagenes.getInstance();
    ServicioUsuarios servicioUsuarios = ServicioUsuariosAppWebImpl.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String nickname = req.getParameter("nickname");
        String nombre = req.getParameter("nombre");
        String apellido = req.getParameter("apellido");
        String email = req.getParameter("email");
        String clave = req.getParameter("password");
        Date fechaNacimiento = HelperFechas.stringToDate(req.getParameter("fechanacimiento"), "yyyy-MM-dd");
        String tipo = req.getParameter("tipo");
        String descripcion = req.getParameter("descripcion");
        String biografia = req.getParameter("biografia");
        String link = req.getParameter("link");
        String imagen = null;

        if (req.getPart("imagen") != null) {
            Part part = req.getPart("imagen");
            imagen = helperImagenes.crearNombreArchivo(part, nickname);
            if (null != imagen) {
                if (!helperImagenes.guardarImagen(part, CarpetaDestinoImagenes.USUARIOS, imagen)) {
                    imagen = null;
                }
            }
        }

        try {
            UsuarioDTO usuario = new UsuarioDTO(null, tipo, nickname, nombre, apellido, email, fechaNacimiento, descripcion, biografia, link, clave, imagen);
            servicioUsuarios.guardarNuevoUsuario(usuario);
        } catch (InnovaModelException e) {
            req.setAttribute("error", e.getMessage());
            req.getRequestDispatcher("principal/signin.jsp").forward(req, resp);
            return;
        }

        RequestDispatcher view = req.getRequestDispatcher("principal/signin.jsp");
        view.forward(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

//    private String guardarImagen(Part part, String nickname) {
//        if (part != null) {
//            
//            String nombreArchivo = helperImagenes.crearNombreArchivo(part, nickname);
//            String imageName = Paths.get(part.getSubmittedFileName()).getFileName().toString();
//
//            String[] split = imageName.split("\\.");
//
//            if (split.length > 1) {
//                String extencion = split[split.length - 1];
//                String nombreArchivo = nickname.replaceAll("\\s+", "").concat(".").concat(extencion);
//                if (helperImagenes.guardarImagen(part, CarpetaDestinoImagenes.USUARIOS, nombreArchivo)) {
//                    return nombreArchivo;
//                }
//            }
//        }
//        return null;
//    }
}
