package edu.innova.webapp.servlets;

import edu.innova.webapp.dtos.FuncionDTO;
import edu.innova.webapp.dtos.UsuarioDTO;
import edu.innova.webapp.helpers.Constantes;
import edu.innova.webapp.helpers.HelperFechas;
import edu.innova.webapp.helpers.HelperImagenes;
import edu.innova.webapp.logica.servicios.ServicioEspectaculos;
import edu.innova.webapp.logica.servicios.ServicioUsuarios;
import edu.innova.webapp.logica.servicios.impl.ServicioEspectaculosAppSwingImpl;
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

@WebServlet(name = "funcion", urlPatterns = {"/funcion"})
@MultipartConfig
public class ServletFuncion extends HttpServlet {

    private final static HelperImagenes helperImagenes = HelperImagenes.getInstance();
    private final static ServicioUsuarios servicioUsuarios = ServicioUsuariosAppSwingImpl.getInstance();
    private final static ServicioEspectaculos servicioEspectaculos = ServicioEspectaculosAppSwingImpl.getInstance();

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
        FuncionDTO nuevaFuncion = new FuncionDTO(nombre, idEspectaculo, fechaInicio, new Date(), usuariosInvitados, null);

        try {
            servicioEspectaculos.altaFuncion(nuevaFuncion);
            req.getRequestDispatcher(String.format("espectaculo/detalle.jsp?idEspectaculo=%s", idEspectaculo)).forward(req, resp);
        } catch (Exception e) {
            req.setAttribute("error", e.getMessage());
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
}
