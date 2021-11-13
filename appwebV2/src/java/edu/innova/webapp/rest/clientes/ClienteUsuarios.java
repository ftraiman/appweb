package edu.innova.webapp.rest.clientes;

import edu.innova.webapp.dtos.RespuestaDTO;
import edu.innova.webapp.dtos.UsuarioDTO;
import java.util.Date;
import java.util.List;

public class ClienteUsuarios extends AbstractRest {

    private static ClienteUsuarios instance;

    private static String USUARIOS_TODOS;
    private static String USUARIOS_LOGIN;
    private static String USUARIOS_MODIFICAR;
    private static String USUARIOS_ALTA;    

    private ClienteUsuarios() {
        USUARIOS_ALTA = PATH_USUARIOS + "/alta";
        USUARIOS_TODOS = PATH_USUARIOS + "/todos";
        USUARIOS_LOGIN = PATH_USUARIOS + "/login";
        USUARIOS_MODIFICAR = PATH_USUARIOS + "/modificar";
    }

    public static ClienteUsuarios getInstance() {
        if (instance == null) {
            instance = new ClienteUsuarios();
        }
        return instance;
    }

    public RespuestaDTO altaUsuario(UsuarioDTO usuario) {
        return (RespuestaDTO) postEntity(USUARIOS_ALTA, usuario, RespuestaDTO.class);
    }

    public UsuarioDTO login(UsuarioDTO usuario) {
        return (UsuarioDTO) postEntity(USUARIOS_LOGIN, usuario, UsuarioDTO.class);
    }

    public List<UsuarioDTO> getTodosLosUsuarios() {
        return getEntities(USUARIOS_TODOS, TIPO_LISTA_USUARIOS);
    }

    public UsuarioDTO getUsuarioPorId(Long id) {
        return (UsuarioDTO) this.getEntityPorId(String.format(PATH_USUARIOS + "/%s", id), UsuarioDTO.class);
    }

    public RespuestaDTO modificarUsuario(UsuarioDTO usuario) {
        return (RespuestaDTO) postEntity(USUARIOS_MODIFICAR, usuario, RespuestaDTO.class);
    }

//    public List<UsuarioDTO> getTodosLosUsuarsios() {
//        try {
//            Response respuesta = client.target(USUARIOS_TODOS)
//                    .request(MediaType.APPLICATION_JSON)
//                    .get();
//            if (respuesta.getStatus() != 200) {
//                ErrorDTO e = respuesta.readEntity(ErrorDTO.class);
//                throw new InnovaModelException(e.getMensaje());
//            }
//            return respuesta.readEntity(TIPO_LISTA_USUARIOS);
//        } catch (Exception e) {
//            throw new InnovaModelException(e.getMessage());
//        }
//    }
//    public UsuarioDTO getUsuarioPorId(Long id) {
//        try {
//            Response respuesta = client.target(String.format(PATH_USUARIOS + "/%s", id))
//                    .request(MediaType.APPLICATION_JSON)
//                    .get();
//            if (respuesta.getStatus() == 400) {
//                ErrorDTO e = respuesta.readEntity(ErrorDTO.class);
//                throw new InnovaModelException(e.getMensaje());
//            }
//            return respuesta.readEntity(UsuarioDTO.class);
//        } catch (Exception e) {
//            throw new InnovaModelException(e.getMessage());
//        }
//    }
    public static void main(String[] args) {
        ClienteUsuarios cr = ClienteUsuarios.getInstance();
//        UsuarioDTO usuario = cr.getUsuarioPorId(Long.valueOf(1));
//        System.err.println(usuario);
//        System.err.println(cr.getUsuarioPorIds(PATH_USUARIOS + "/1", UsuarioDTO.class));
//        cr.getUsuarioPorIds(PATH_USUARIOS + "/1", UsuarioDTO.class);
//        cr.clienteRest()<UsuarioDTO>;
//        System.err.println(cr.getTodosLosUsuarios());

//    System.err.println(cr.getEntityPorId(PATH_USUARIOS + "/1", UsuarioDTO.class));
//        System.err.println(cr.getEntities(USUARIOS_TODOS, TIPO_LISTA_USUARIOS));
//        System.err.println(cr.getUsuarioPorId(Long.valueOf(1)));
        try {
            RespuestaDTO respuesta = (cr.altaUsuario(new UsuarioDTO("espectador", "elnick", "nombre", "apellido", "soy@email.com", new Date(), "granclave")));;
            System.err.println(respuesta);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

//    List<Customer> customers =
//            client.target("http://localhost:8080/customer/webapi/Customer")
//            .path("all")
//            .request(MediaType.APPLICATION_XML)
//            .get(new GenericType<List<Customer>>() {});
}
