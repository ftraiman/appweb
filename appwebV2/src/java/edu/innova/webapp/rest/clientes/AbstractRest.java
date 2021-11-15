package edu.innova.webapp.rest.clientes;

import edu.innova.webapp.dtos.CategoriaDTO;
import edu.innova.webapp.dtos.ErrorDTO;
import edu.innova.webapp.dtos.EspectaculoDTO;
import edu.innova.webapp.dtos.FuncionDTO;
import edu.innova.webapp.dtos.PlataformaDTO;
import edu.innova.webapp.dtos.RespuestaDTO;
import edu.innova.webapp.dtos.UsuarioDTO;
import edu.innova.webapp.exceptions.InnovaModelException;
import edu.innova.webapp.helpers.HelperProperties;
import java.util.List;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

abstract class AbstractRest<T> {

    private Client client;
    
    protected static String RUTA_BASE_WS;
    protected static String PATH_USUARIOS;
    protected static String PATH_ESPECTACULOS;
    protected static String PATH_FUNCIONES;
    protected static String PATH_SEGUIDORES;
    protected static String PATH_PAQUETES;
    protected static String PATH_CANJES;
    protected static String PATH_MISC;
    
    protected static final GenericType<List<CategoriaDTO>> TIPO_LISTA_CATEGORIAS = new GenericType<List<CategoriaDTO>>() {};
    protected static final GenericType<List<PlataformaDTO>> TIPO_LISTA_PLATAFORMAS = new GenericType<List<PlataformaDTO>>() {};
    protected static final GenericType<List<UsuarioDTO>> TIPO_LISTA_USUARIOS = new GenericType<List<UsuarioDTO>>() {};
    protected static final GenericType<List<FuncionDTO>> TIPO_LISTA_FUNCIONES = new GenericType<List<FuncionDTO>>() {};
    protected static final GenericType<List<EspectaculoDTO>> TIPO_LISTA_ESPECTACULOS = new GenericType<List<EspectaculoDTO>>() {};
    
    public AbstractRest() {
        this.client = ClientBuilder.newClient();
        if (RUTA_BASE_WS == null) {
            HelperProperties hp = new HelperProperties().getInstance();
            RUTA_BASE_WS = hp.getRUTA_BASE_WS();
            PATH_USUARIOS = RUTA_BASE_WS + "/usuarios";
            PATH_ESPECTACULOS = RUTA_BASE_WS + "/espectaculos";
            PATH_FUNCIONES = RUTA_BASE_WS + "/funciones";
            PATH_SEGUIDORES = RUTA_BASE_WS + "/seguidores";
            PATH_PAQUETES = RUTA_BASE_WS + "/paquetes";
            PATH_CANJES = RUTA_BASE_WS + "/canjes";
            PATH_MISC= RUTA_BASE_WS + "/miscelaneas";
        }
    }
    
    public <T> T getEntityPorId(String path, Class<T> clazz) {
        try {
            Response respuesta = client.target(path)
                    .request(MediaType.APPLICATION_JSON)
                    .get();
            if (respuesta.getStatus() == 400) {
                ErrorDTO e = respuesta.readEntity(ErrorDTO.class);
                throw new InnovaModelException(e.getMensaje());
            }
            return respuesta.readEntity(clazz);
        } catch (Exception e) {
            e.printStackTrace();
            throw new InnovaModelException(e.getMessage());
        }
    }
    
//    public <T> T postEntity(String path, Class<T> clazz) {
//        try {
//            Response respuesta = client.target(path)
//                    .request(MediaType.APPLICATION_JSON)
//                    .post(Entity.entity(clazz, MediaType.APPLICATION_JSON));
//            if (respuesta.getStatus() == 400) {
//                ErrorDTO e = respuesta.readEntity(ErrorDTO.class);
//                throw new InnovaModelException(e.getMensaje());
//            }
//            return respuesta.readEntity(clazz);
//        } catch (Exception e) {
//            e.printStackTrace();
//            throw new InnovaModelException(e.getMessage());
//        }
//    }
    
    public <T> T postEntity(String path, T contenido, Class<T> claseRespuesta) {
        try {
            Response respuesta = client.target(path)
                    .request(MediaType.APPLICATION_JSON)
                    .post(Entity.json(contenido));
            if (respuesta.getStatus() == 400) {
                RespuestaDTO e = respuesta.readEntity(RespuestaDTO.class);
                throw new InnovaModelException(e.getMensaje());
            }
            return respuesta.readEntity(claseRespuesta);
        } catch (Exception e) {
            e.printStackTrace();
            throw new InnovaModelException(e.getMessage());
        }
    }
    
    public <T> T deleteEntity(String path, T contenido, Class<T> claseRespuesta) {
        try {
            Response respuesta = client.target(path)
                    .request(MediaType.APPLICATION_JSON)
                    .method("delete", Entity.json(contenido));
            if (respuesta.getStatus() == 400) {
                RespuestaDTO e = respuesta.readEntity(RespuestaDTO.class);
                throw new InnovaModelException(e.getMensaje());
            }
            return respuesta.readEntity(claseRespuesta);
        } catch (Exception e) {
            e.printStackTrace();
            throw new InnovaModelException(e.getMessage());
        }
    }
    
    public <T> List<T> getEntities(String path, GenericType<List<T>> genericType) {
        try {
            Response respuesta = client.target(path)
                    .request(MediaType.APPLICATION_JSON)
                    .get();
            if (respuesta.getStatus() == 400) {
                ErrorDTO e = respuesta.readEntity(ErrorDTO.class);
                throw new InnovaModelException(e.getMensaje());
            }
            return respuesta.readEntity(genericType);
        } catch (Exception e) {
            e.printStackTrace();
            throw new InnovaModelException(e.getMessage());
        }
    }
    
//    public String getRUTA_BASE_WS() {
//        if (RUTA_BASE_WS == null) {
//            HelperProperties hp = new HelperProperties().getInstance();
//            RUTA_BASE_WS = hp.getRUTA_BASE_WS();
//        }
//        return RUTA_BASE_WS;
//    }
}
