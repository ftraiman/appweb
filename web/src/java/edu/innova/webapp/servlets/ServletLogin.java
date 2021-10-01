package edu.innova.webapp.servlets;

import edu.innova.webapp.entidades.Usuario;
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

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String email = req.getParameter("email");
        String clave = req.getParameter("clave");
        System.err.println(email);
        Usuario usuario = new Usuario();
        usuario.setNickname(email);

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

////        super.doGet(req, resp); //To change body of generated methods, choose Tools | Templates.
//        RequestDispatcher view = req.getRequestDispatcher("mierda.jsp");
//        view.forward(req, resp);
    }

    public static Usuario getUsuarioLogueado(HttpServletRequest req) {
        HttpSession session = req.getSession();
        return (Usuario) session.getAttribute("usuario");
    }

}
