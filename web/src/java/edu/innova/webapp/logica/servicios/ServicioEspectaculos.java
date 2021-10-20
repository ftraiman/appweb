package edu.innova.webapp.logica.servicios;

import edu.innova.webapp.dtos.EspectaculoDTO;
import edu.innova.webapp.dtos.FuncionDTO;
import java.util.List;

public interface ServicioEspectaculos {

    void altaEspectaculo(EspectaculoDTO espectaculo);

    EspectaculoDTO getEspectaculoPorId(Long idEspectaculo);

    List<EspectaculoDTO> getEspectaculosPorIdArtista(Long idArtista);

    void altaFuncion(FuncionDTO funcion);

    FuncionDTO getFuncionPorId(Long idFuncion);
    
    List<EspectaculoDTO> getEspectaculosPorCategoriaPlataforma(Long idPlataforma, Long idCategoria);
}
