package edu.innova.webapp.logica.servicios;

import edu.innova.webapp.dtos.EspectaculoDTO;
import edu.innova.webapp.dtos.PaqueteDTO;
import java.util.List;

public interface ServicioPaquetes {
    
    void altaPaquete(PaqueteDTO paquete, Long idArtista);
    
    List<PaqueteDTO> getPaquetesPorIdArtista(Long idArtista);
    
    PaqueteDTO getPaquetePorId(Long idPaquete);
    
    List<EspectaculoDTO> getEspectaculosNoIncluidosEnPaquete(Long idUsuario, Long idPaquete);
    
    void altaEspectaculoEnPaquete(Long idEspectaculo, Long idPaquete);
    
    List<PaqueteDTO> getPaquetesPorIdEspectaculo(Long idEspectaculo);
    
    List<PaqueteDTO> getTodosLosPaquetes();
    
    void altaUsuarioEnPaquete(Long idUsuario, Long idPaquete);
    
    List<PaqueteDTO> getPaquetesCompradosPorIdUsuario(Long idUsuario);
}
