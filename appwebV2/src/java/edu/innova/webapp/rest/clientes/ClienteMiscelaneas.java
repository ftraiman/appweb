package edu.innova.webapp.rest.clientes;

import edu.innova.webapp.dtos.CategoriaDTO;
import edu.innova.webapp.dtos.PlataformaDTO;
import java.util.List;

public class ClienteMiscelaneas extends AbstractRest {

    private static ClienteMiscelaneas instance;

    private static String MISC_CATEGORIAS;
    private static String MISC_PLATAFORMAS;

    private ClienteMiscelaneas() {
        MISC_CATEGORIAS = PATH_MISC + "/categorias"; //(GET)
        MISC_PLATAFORMAS = PATH_MISC + "/plataformas"; //(GET)
    }

    public static ClienteMiscelaneas getInstance() {
        if (instance == null) {
            instance = new ClienteMiscelaneas();
        }
        return instance;
    }

    public List<CategoriaDTO> getTodosLasCategorias() {
        return getEntities(MISC_CATEGORIAS, TIPO_LISTA_CATEGORIAS);
    }
    
    public List<PlataformaDTO> getTodosLasPlataformas() {
        return getEntities(MISC_PLATAFORMAS, TIPO_LISTA_PLATAFORMAS);
    }
    
}
