package edu.innova.webapp.rest.clientes;

import edu.innova.webapp.dtos.CanjeTresPorUnoDTO;
import edu.innova.webapp.dtos.FuncionDTO;
import edu.innova.webapp.dtos.InformacionCanjeTresPorUnoDTO;
import edu.innova.webapp.dtos.RegistroUsurioEnFuncionRequestDTO;
import edu.innova.webapp.dtos.RespuestaDTO;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class ClienteFunciones extends AbstractRest {

    private static ClienteFunciones instance;

    private static String FUNCION_ALTA;
    private static String FUNCION_POR_ID;
    private static String FUNCION_USUARIO_REGISTRADO;
    private static String FUNCION_ESTA_COMPLETA;
    private static String FUNCION_CANJE_INFO;
    private static String FUNCION_CANJE;
    private static String FUNCION_REGISTRO_USUARIO;
    private static String FUNCION_FAVORITAS_ALTA;
    private static String FUNCION_FAVORITAS_BAJA;
    private static String FUNCION_FAVORITAS_CONSULTA;

    private ClienteFunciones() {
        FUNCION_ALTA = PATH_FUNCIONES + "/alta"; //(POST)
        FUNCION_POR_ID = PATH_FUNCIONES + "/%s"; //(GET)
        FUNCION_USUARIO_REGISTRADO = PATH_FUNCIONES + "/funcion/%s/usuario/%s"; //(GET)
        FUNCION_ESTA_COMPLETA = PATH_FUNCIONES + "/completa/%s"; //(GET)
        FUNCION_CANJE_INFO = PATH_FUNCIONES + "/canje/%s"; //(GET)
        FUNCION_CANJE = PATH_FUNCIONES + "/canje"; //(POST)
        FUNCION_REGISTRO_USUARIO = PATH_FUNCIONES + "/registrousuario"; //(POST)
        FUNCION_FAVORITAS_ALTA = PATH_FUNCIONES + "/favoritas"; //(POST)
        FUNCION_FAVORITAS_ALTA = PATH_FUNCIONES + "/favoritas"; //(DELETE)
        FUNCION_FAVORITAS_CONSULTA = PATH_FUNCIONES + "/favoritas/%s"; //(GET)
    }

    public static ClienteFunciones getInstance() {
        if (instance == null) {
            instance = new ClienteFunciones();
        }
        return instance;
    }

    public RespuestaDTO altaFuncion(FuncionDTO funcion) {
        return (RespuestaDTO) postEntity(FUNCION_ALTA, funcion, RespuestaDTO.class);
    }

    public FuncionDTO getFuncionPorId(Long idFuncion) {
        String path = String.format(FUNCION_POR_ID, idFuncion);
        return (FuncionDTO) getEntityPorId(path, FuncionDTO.class);
    }
    
    public RespuestaDTO isUsuarioRegistradoEnFuncion(Long idFuncion, Long idUsuario) {
        String path = String.format(FUNCION_USUARIO_REGISTRADO, idFuncion, idUsuario);
        return (RespuestaDTO) getEntityPorId(path, RespuestaDTO.class);
    }
    
    public RespuestaDTO registroUsuarioEnFuncion(Long idFuncion, Long idUsuario, Date fechaRegistro, BigDecimal costo) {
        RegistroUsurioEnFuncionRequestDTO ruefr = new RegistroUsurioEnFuncionRequestDTO(idFuncion, idUsuario, fechaRegistro, costo);
        return (RespuestaDTO) postEntity(FUNCION_REGISTRO_USUARIO, ruefr, RespuestaDTO.class);
    }
    
    public RespuestaDTO isFuncionCompleta(Long idFuncion) {
        String path = String.format(FUNCION_ESTA_COMPLETA, idFuncion);
        return (RespuestaDTO) getEntityPorId(path, RespuestaDTO.class);
    }
    
    public InformacionCanjeTresPorUnoDTO getInfoCanjeTresPorUno(Long idUsuario) {
        String path = String.format(FUNCION_CANJE_INFO, idUsuario);
        return (InformacionCanjeTresPorUnoDTO) getEntityPorId(path, InformacionCanjeTresPorUnoDTO.class);
    }
    
    public List<FuncionDTO> getFuncionesDeUsuarioParaCanjear(Long idUsuario) {
        String path = String.format(FUNCION_CANJE_INFO, idUsuario);
        return getEntities(path, TIPO_LISTA_FUNCIONES);
    }
    
    public RespuestaDTO canjeTresPorUnoDTO(CanjeTresPorUnoDTO canjeTresPorUno) {
        return (RespuestaDTO) postEntity(FUNCION_CANJE, canjeTresPorUno, RespuestaDTO.class);
    }
 
    public RespuestaDTO registroUsuarioEnFuncion(RegistroUsurioEnFuncionRequestDTO entidad) {
        return (RespuestaDTO) postEntity(FUNCION_REGISTRO_USUARIO, entidad, RespuestaDTO.class);
    }
    
    public RespuestaDTO altaFuncionFavorita(RegistroUsurioEnFuncionRequestDTO entidad) {
        return (RespuestaDTO) postEntity(FUNCION_FAVORITAS_ALTA, entidad, RespuestaDTO.class);
    }
    
    public RespuestaDTO bajaFuncionFavorita(RegistroUsurioEnFuncionRequestDTO entidad) {
        return (RespuestaDTO) postEntity(FUNCION_FAVORITAS_BAJA, entidad, RespuestaDTO.class);
    }
    
    public List<FuncionDTO> getFuncionFavoritasesDeUsuario(Long idUsuario) {
        String path = String.format(FUNCION_FAVORITAS_CONSULTA, idUsuario);
        return getEntities(path, TIPO_LISTA_FUNCIONES);
    }
}
