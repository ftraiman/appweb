package edu.innova.webapp.rest.clientes;

import edu.innova.webapp.dtos.CanjeRequestDTO;
import edu.innova.webapp.dtos.CanjeTresPorUnoDTO;
import edu.innova.webapp.dtos.RespuestaDTO;

public class ClienteCanjes extends AbstractRest {

    private static ClienteCanjes instance;

    private static String CANJE_ALTA;
    private static String CANJE_ESPECTACULO_UTILIZADO;

    private ClienteCanjes() {
        CANJE_ALTA = PATH_CANJES + "/alta"; //(POST)
        CANJE_ESPECTACULO_UTILIZADO = PATH_CANJES + "/utilizado"; //(POST)
    }

    public static ClienteCanjes getInstance() {
        if (instance == null) {
            instance = new ClienteCanjes();
        }
        return instance;
    }
    
    public RespuestaDTO altaCanjeTresPorUno(CanjeTresPorUnoDTO canje) {
        return (RespuestaDTO) postEntity(CANJE_ALTA, canje, RespuestaDTO.class);
    }
    
    public RespuestaDTO altaEspectaculoPaqueteUtilizado(Long idUsuario,Long idPaquete, Long idEspectaculo) {
        CanjeRequestDTO crdto = new CanjeRequestDTO(idUsuario, idPaquete, idEspectaculo);
        return (RespuestaDTO) postEntity(CANJE_ESPECTACULO_UTILIZADO, crdto, RespuestaDTO.class);
    } 
}
