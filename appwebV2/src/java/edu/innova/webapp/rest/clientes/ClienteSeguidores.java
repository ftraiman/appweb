package edu.innova.webapp.rest.clientes;

import edu.innova.webapp.dtos.RespuestaDTO;
import edu.innova.webapp.dtos.SeguidoresRequestDTO;
import edu.innova.webapp.dtos.UsuarioDTO;
import java.util.List;
import javax.ws.rs.core.GenericType;

public class ClienteSeguidores extends AbstractRest {

    private static ClienteSeguidores instance;

    private static String SEGUIDORES_SIGUE;
    private static String SEGUIDORES_LO_SIGUEN;
    private static String SEGUIDORES_SEGUIR;
    private static String SEGUIDORES_DEJAR_DE_SEGUIR;


    private static final GenericType<List<UsuarioDTO>> TIPO_LISTA_USUARIOS = new GenericType<List<UsuarioDTO>>() {
    };

    private ClienteSeguidores() {
        SEGUIDORES_SIGUE = PATH_SEGUIDORES + "/quesigue/%s"; //(GET)
        SEGUIDORES_LO_SIGUEN = PATH_SEGUIDORES + "/quelosiguen/%s"; //(GET)
        SEGUIDORES_SEGUIR = PATH_SEGUIDORES + "/alta"; //(POST)
        SEGUIDORES_DEJAR_DE_SEGUIR = PATH_SEGUIDORES + "/baja"; //(POST)
    }

    public static ClienteSeguidores getInstance() {
        if (instance == null) {
            instance = new ClienteSeguidores();
        }
        return instance;
    }

    public List<UsuarioDTO> getUsuariosQueSigue(Long idUsuario) {
        String path = String.format(SEGUIDORES_SIGUE, idUsuario);
        return getEntities(path, TIPO_LISTA_USUARIOS);
    }

    public List<UsuarioDTO> getUsuariosQueLoSiguen(Long idUsuario) {
        String path = String.format(SEGUIDORES_LO_SIGUEN, idUsuario);
        return getEntities(path, TIPO_LISTA_USUARIOS);
    }

    public RespuestaDTO seguirUsuario(SeguidoresRequestDTO request) {
        return (RespuestaDTO) postEntity(SEGUIDORES_SEGUIR, request, RespuestaDTO.class);
    }
    
    public RespuestaDTO dejarDeSeguirUsuario(SeguidoresRequestDTO request) {
        return (RespuestaDTO) postEntity(SEGUIDORES_DEJAR_DE_SEGUIR, request, RespuestaDTO.class);
    }
}
