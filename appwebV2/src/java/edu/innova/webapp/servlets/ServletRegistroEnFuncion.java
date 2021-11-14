package edu.innova.webapp.servlets;

import edu.innova.webapp.dtos.CanjeTresPorUnoDTO;
import edu.innova.webapp.dtos.EspectaculoDTO;
import edu.innova.webapp.dtos.InformacionFuncionDTO;
import edu.innova.webapp.dtos.PaqueteDTO;
import edu.innova.webapp.dtos.UsuarioDTO;
import edu.innova.webapp.helpers.Constantes;
import edu.innova.webapp.logica.servicios.ServicioEspectaculos;
import edu.innova.webapp.logica.servicios.ServicioPaquetes;
import edu.innova.webapp.logica.servicios.ServicioUsuarios;
import edu.innova.webapp.logica.servicios.impl.ServicioEspectaculosAppSwingImpl;
import edu.innova.webapp.logica.servicios.impl.ServicioPaquetesAppSwingImpl;
import edu.innova.webapp.logica.servicios.impl.ServicioUsuariosAppSwingImpl;
import java.io.IOException;
import java.math.BigDecimal;
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

@WebServlet(name = "registrofuncion", urlPatterns = {"/registrofuncion"})
@MultipartConfig
public class ServletRegistroEnFuncion extends HttpServlet {

    private final static ServicioUsuarios servicioUsuarios = ServicioUsuariosAppSwingImpl.getInstance();
    private final static ServicioEspectaculos servicioEspectaculos = ServicioEspectaculosAppSwingImpl.getInstance();
    private final static ServicioPaquetes servicioPaquetes = ServicioPaquetesAppSwingImpl.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();
        UsuarioDTO usuarioLogueado = (UsuarioDTO) session.getAttribute(Constantes.USUARIO);

        Long idFuncion = Long.valueOf(req.getParameter("idFuncion"));
        InformacionFuncionDTO infoFuncion = ServletFuncion.getInformacionFuncion(idFuncion, req);
        if (isDatosInvalidos(infoFuncion)) {
            // TODO redirect cuando pasa esto
        }
        String operacion = req.getParameter("operacion");
        Long idUsuario = usuarioLogueado.getId();

        if ("compradirecta".equalsIgnoreCase(operacion)) {
            try {
                BigDecimal costo = infoFuncion.getEspectaculo().getCosto();
                servicioEspectaculos.registrarUsuarioEnFuncion(idFuncion, idUsuario, new Date(), costo);
                req.setAttribute(Constantes.MENSAJE, "La Funcion se compro correctamente");
                req.getRequestDispatcher("usuario/miperfil.jsp").forward(req, resp);
            } catch (Exception e) {
                req.setAttribute(Constantes.ERROR, "Debes de seleccionar 3 funciones para canjear");
                req.getRequestDispatcher("funcion/registro.jsp").forward(req, resp);
                return;
            }

        } else if ("canjetresporuno".equalsIgnoreCase(operacion)) {
            try {
                String[] idsFuncionParaCambio = req.getParameterValues("tresporuno");
                Long idEspectaculo = Long.valueOf(req.getParameter("idEspectaculo"));

                if (idsFuncionParaCambio.length < 3) {
                    req.setAttribute(Constantes.ERROR, "Debes de seleccionar 3 funciones para canjear");
                    req.getRequestDispatcher("funcion/registro.jsp").forward(req, resp);
                    return;
                }
                String funcionesCanjeadas = Stream.of(idsFuncionParaCambio).collect(Collectors.joining(","));

                CanjeTresPorUnoDTO ctpudto = new CanjeTresPorUnoDTO(idUsuario, idFuncion, funcionesCanjeadas, idEspectaculo);

                servicioEspectaculos.canjeTresPorUno(ctpudto);
                req.setAttribute(Constantes.MENSAJE, "El canje de 3x1 se procesó correctamente");
                req.getRequestDispatcher("usuario/miperfil.jsp").forward(req, resp);
                return;
            } catch (Exception e) {
                req.setAttribute(Constantes.ERROR, e.getMessage());
                req.getRequestDispatcher("funcion/registro.jsp").forward(req, resp);
                return;
            }
        } else if ("canjepaquete".equalsIgnoreCase(operacion)) {
            try {
                Long idPaquete = Long.valueOf(req.getParameter("idPaquete"));
                PaqueteDTO paquete = servicioPaquetes.getPaquetePorId(idPaquete);
                
                EspectaculoDTO espectaculo = infoFuncion.getEspectaculo();
                
                servicioPaquetes.canjePaquete(idUsuario, idFuncion, paquete, espectaculo);
                req.setAttribute(Constantes.MENSAJE, "El canje de por paquete se procesó correctamente");
                req.getRequestDispatcher("usuario/miperfil.jsp").forward(req, resp);
                return;
                
            } catch (Exception e) {
                req.setAttribute(Constantes.ERROR, e.getMessage());
                req.getRequestDispatcher("funcion/registro.jsp").forward(req, resp);
                return;
            }

        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    public static Boolean isDatosInvalidos(InformacionFuncionDTO infoFuncion) {
        return infoFuncion.getIsFuncionCompleta() || infoFuncion.getIsUsuarioRegistradoEnFuncion() || !infoFuncion.getIsUsuarioLogueado();
    }

}
