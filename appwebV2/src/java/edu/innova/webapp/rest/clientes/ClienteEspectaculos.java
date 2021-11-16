package edu.innova.webapp.rest.clientes;

import edu.innova.webapp.dtos.EspectaculoDTO;
import edu.innova.webapp.dtos.RespuestaDTO;
import java.util.ArrayList;
import java.util.List;
import uy.innova.ws.dtos.EspectaculosRequest;

public class ClienteEspectaculos extends AbstractRest {

    private static ClienteEspectaculos instance;

    private static String ESPECTACULO_ALTA;
    private static String ESPECTACULO_POR_ID;
    private static String ESPECTACULOS_POR_ARTISTA;
    private static String ESPECTACULOS_POR_CATEGORIA_PLATAFORMA;
    private static String ESPECTACULOS_TODOS;
    private static String ESPECTACULOS_FAVORITOS;
    private static String ESPECTACULOS_FAVORITOS_BUSQUEDA;
    private static String ESPECTACULOS_FINALIZAR;

    private ClienteEspectaculos() {
        ESPECTACULO_ALTA = PATH_ESPECTACULOS + "/alta"; //(POST)
        ESPECTACULO_POR_ID = PATH_ESPECTACULOS + "/%s"; //(GET)
        ESPECTACULOS_POR_ARTISTA = PATH_ESPECTACULOS + "/idartista/%s"; //(GET)
        ESPECTACULOS_POR_CATEGORIA_PLATAFORMA = PATH_ESPECTACULOS + "/plataforma/%s/categoria/%s"; //(GET)
        ESPECTACULOS_TODOS = PATH_ESPECTACULOS + "/todos"; //(GET)
        ESPECTACULOS_FAVORITOS = PATH_ESPECTACULOS + "/favoritos"; //(POST)
        ESPECTACULOS_FAVORITOS_BUSQUEDA = PATH_ESPECTACULOS + "/favoritos/%s";
        ESPECTACULOS_FINALIZAR = PATH_ESPECTACULOS + "/finalizar";
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
    
    public RespuestaDTO favoritos(EspectaculosRequest request) {
        return (RespuestaDTO) postEntity(ESPECTACULOS_FAVORITOS, request, RespuestaDTO.class);
    }
    
    public List<EspectaculoDTO> getEspectaculosFavoritosPorUsuario(Long idUsuario) {
        String path = String.format(ESPECTACULOS_FAVORITOS_BUSQUEDA, idUsuario);
        return getEntities(path, TIPO_LISTA_ESPECTACULOS);
    }
    
    public RespuestaDTO finalizar(EspectaculosRequest request) {
        return (RespuestaDTO) postEntity(ESPECTACULOS_FINALIZAR, request, RespuestaDTO.class);
    }
}
