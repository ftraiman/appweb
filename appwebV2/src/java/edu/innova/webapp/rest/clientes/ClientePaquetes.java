package edu.innova.webapp.rest.clientes;

import edu.innova.webapp.dtos.EspectaculoDTO;
import edu.innova.webapp.dtos.PaqueteDTO;
import edu.innova.webapp.dtos.PaqueteRequestDTO;
import edu.innova.webapp.dtos.RespuestaDTO;
import java.util.List;
import javax.ws.rs.core.GenericType;

public class ClientePaquetes extends AbstractRest {

    private static ClientePaquetes instance;

    private static String PAQUETE_ALTA;
    private static String PAQUETE_ALTA_ESPECTACULO;
    private static String PAQUETE_DE_ARTISTA;
    private static String PAQUETE_ESPECTACULOS_NO_INCLUIDOS;
    private static String PAQUETE_ALTA_USUARIO;
    private static String PAQUETE_DE_ESPECTACULO;
    private static String PAQUETE_DE_USUARIO;
    private static String PAQUETE_TODOS;
    private static String PAQUETE_POR_ID;
    private static String PAQUETE_CON_LA_FUNCION;

    private static final GenericType<List<PaqueteDTO>> TIPO_LISTA_PAQUETES = new GenericType<List<PaqueteDTO>>() {
    };
    private static final GenericType<List<EspectaculoDTO>> TIPO_LISTA_ESPECTACULOS = new GenericType<List<EspectaculoDTO>>() {
    };

    private ClientePaquetes() {
        PAQUETE_ALTA = PATH_PAQUETES + "/alta"; //(POST)
        PAQUETE_ALTA_ESPECTACULO = PATH_PAQUETES + "/alta/espectaculo"; //(POST)
        PAQUETE_ALTA_USUARIO = PATH_PAQUETES + "/alta/usuario"; //(POST)
        PAQUETE_DE_ARTISTA = PATH_PAQUETES + "/artista/%s"; //(GET)
        PAQUETE_DE_ESPECTACULO = PATH_PAQUETES + "/espectaculo/%s"; //(GET)
        PAQUETE_POR_ID = PATH_PAQUETES + "/%s"; //(GET)
        PAQUETE_ESPECTACULOS_NO_INCLUIDOS = PATH_PAQUETES + "/espectaculosnoincluidos/usuario/%s/paquete/%s"; //(GET)
        PAQUETE_DE_USUARIO = PATH_PAQUETES + "/usuario/%s"; //(GET) 
        PAQUETE_TODOS = PATH_PAQUETES + "/todos";
        PAQUETE_CON_LA_FUNCION = PATH_PAQUETES + "/usuario/%s/funcion/%s";
    }

    public static ClientePaquetes getInstance() {
        if (instance == null) {
            instance = new ClientePaquetes();
        }
        return instance;
    }

    public RespuestaDTO altaPaquete(PaqueteDTO paquete) {
        return (RespuestaDTO) postEntity(PAQUETE_ALTA, paquete, RespuestaDTO.class);
    }

    public PaqueteDTO getPaquetePorId(Long idPaquete) {
        String path = String.format(PAQUETE_POR_ID, idPaquete);
        return (PaqueteDTO) getEntityPorId(path, PaqueteDTO.class);
    }

    public List<PaqueteDTO> getPaquetePorIdArtista(Long idArtista) {
        String path = String.format(PAQUETE_DE_ARTISTA, idArtista);
        return getEntities(path, TIPO_LISTA_PAQUETES);
    }

    public List<EspectaculoDTO> getEspectaculosNoIncluidosEnPaquete(Long idUsuario, Long idPaquete) {
        String path = String.format(PAQUETE_ESPECTACULOS_NO_INCLUIDOS, idUsuario, idPaquete);
        return getEntities(path, TIPO_LISTA_ESPECTACULOS);
    }

    public RespuestaDTO altaEspectaculoEnPaquete(Long idEspectaculo, Long idPaquete) {
        PaqueteRequestDTO prdto = new PaqueteRequestDTO(idEspectaculo, idPaquete);
        return (RespuestaDTO) postEntity(PAQUETE_ALTA_ESPECTACULO, prdto, RespuestaDTO.class);
    }

    public RespuestaDTO altaUsuarioEnPaquete(Long idUsuario, Long idPaquete) {
        PaqueteRequestDTO prdto = new PaqueteRequestDTO();
        prdto.setIdUsuario(idUsuario);
        prdto.setIdPaquete(idPaquete);
        return (RespuestaDTO) postEntity(PAQUETE_ALTA_USUARIO, prdto, RespuestaDTO.class);
    }

    public List<PaqueteDTO> getPaquetesPorIdEspectaculo(Long idEspectaculo) {
        String path = String.format(PAQUETE_DE_ESPECTACULO, idEspectaculo);
        return getEntities(path, TIPO_LISTA_PAQUETES);
    }

    public List<PaqueteDTO> getPaquetesCompradosPorIdUsuario(Long idUsuario) {
        String path = String.format(PAQUETE_DE_USUARIO, idUsuario);
        return getEntities(path, TIPO_LISTA_PAQUETES);
    }

    public List<PaqueteDTO> getTodosLosPaquetes() {
        return getEntities(PAQUETE_TODOS, TIPO_LISTA_PAQUETES);
    }

    public List<PaqueteDTO> getPaquetesConLaFuncion(Long idUsuario, Long idFuncion) {
        String path = String.format(PAQUETE_CON_LA_FUNCION, idUsuario, idFuncion);
        return getEntities(path, TIPO_LISTA_PAQUETES);
    }
}
