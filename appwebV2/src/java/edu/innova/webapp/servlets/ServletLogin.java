package edu.innova.webapp.servlets;


import edu.innova.webapp.dtos.UsuarioDTO;
import edu.innova.webapp.exceptions.InnovaModelException;
import edu.innova.webapp.helpers.Constantes;
import edu.innova.webapp.logica.servicios.ServicioUsuarios;
import edu.innova.webapp.logica.servicios.impl.ServicioUsuariosAppSwingImpl;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "login", urlPatterns = {"/login"})
public class ServletLogin extends HttpServlet {
    
    private ServicioUsuarios servicioUsuarios = ServicioUsuariosAppSwingImpl.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String identificadorUsuario = req.getParameter("email");
        String clave = req.getParameter("clave");

        UsuarioDTO usuario = null;
        try {
             usuario = servicioUsuarios.getUsuarioDTOPorCredenciales(identificadorUsuario, identificadorUsuario, clave);
             
        } catch (InnovaModelException e) {
            req.setAttribute(Constantes.ERROR, e.getMessage());
            RequestDispatcher view = req.getRequestDispatcher("usuario/login.jsp");
            view.forward(req, resp);
            return;
        }
        
        HttpSession session = req.getSession();
        session.setAttribute("usuario", usuario);

        RequestDispatcher view = req.getRequestDispatcher("principal/index.jsp");
        view.forward(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if (null != req.getParameter("logout")) {
            HttpSession session = req.getSession();
            session.removeAttribute("usuario");
            resp.sendRedirect("principal/index.jsp");
        }

    }

    public static UsuarioDTO getUsuarioLogueado(HttpServletRequest req) {
        HttpSession session = req.getSession();
        return (UsuarioDTO) session.getAttribute("usuario");
    }

}
