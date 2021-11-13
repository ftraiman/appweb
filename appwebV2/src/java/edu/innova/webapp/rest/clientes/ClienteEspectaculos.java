package edu.innova.webapp.rest.clientes;

import edu.innova.webapp.dtos.EspectaculoDTO;
import edu.innova.webapp.dtos.RespuestaDTO;
import java.util.ArrayList;
import java.util.List;

public class ClienteEspectaculos extends AbstractRest {

    private static ClienteEspectaculos instance;

    private static String ESPECTACULO_ALTA;
    private static String ESPECTACULO_POR_ID;
    private static String ESPECTACULOS_POR_ARTISTA;
    private static String ESPECTACULOS_POR_CATEGORIA_PLATAFORMA;
    private static String ESPECTACULOS_TODOS;

    private ClienteEspectaculos() {
        ESPECTACULO_ALTA = PATH_ESPECTACULOS + "/alta"; //(POST)
        ESPECTACULO_POR_ID = PATH_ESPECTACULOS + "/%s"; //(GET)
        ESPECTACULOS_POR_ARTISTA = PATH_ESPECTACULOS + "/idartista/%s"; //(GET)
        ESPECTACULOS_POR_CATEGORIA_PLATAFORMA = PATH_ESPECTACULOS +"/plataforma/%s/categoria/%s"; //(GET)
        ESPECTACULOS_TODOS = PATH_ESPECTACULOS + "/todos"; //(GET)
    }

    public static ClienteEspectaculos getInstance() {
        if (instance == null) {
            instance = new ClienteEspectaculos();
        }
        return instance;
    }

    public RespuestaDTO altaEspectaculo(EspectaculoDTO espectaculo) {
        return (RespuestaDTO) postEntity(ESPECTACULO_ALTA, espectaculo, RespuestaDTO.class);
    }

    public List<EspectaculoDTO> getEspectaculosPorArtista(Long idArtista) {
        String path = String.format(ESPECTACULOS_POR_ARTISTA, idArtista);
        return getEntities(path, TIPO_LISTA_ESPECTACULOS);
    }
    
    public EspectaculoDTO getEspectaculoPorId(Long idEspectaculo) {
        String path = String.format(ESPECTACULO_POR_ID, idEspectaculo);
        return (EspectaculoDTO) getEntityPorId(path, EspectaculoDTO.class);
    }
    
    public List<EspectaculoDTO> getEspectaculosPorPlataformaCategoria(Long idPlataforma, Long idCategoria) {
        String path = String.format(ESPECTACULOS_POR_CATEGORIA_PLATAFORMA, idPlataforma, idCategoria);
        return getEntities(path, TIPO_LISTA_ESPECTACULOS);
    }
    
    public List<EspectaculoDTO> getTodosLosEspectaculos() {
        try {
            return getEntities(ESPECTACULOS_TODOS, TIPO_LISTA_ESPECTACULOS);
        } catch (Exception e) {
            return new ArrayList<>();
        }
        
    }
 
}
