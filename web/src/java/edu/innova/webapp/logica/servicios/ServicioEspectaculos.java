package edu.innova.webapp.logica.servicios;

import edu.innova.webapp.dtos.CanjeTresPorUnoDTO;
import edu.innova.webapp.dtos.EspectaculoDTO;
import edu.innova.webapp.dtos.FuncionDTO;
import edu.innova.webapp.dtos.InformacionCanjeTresPorUno;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public interface ServicioEspectaculos {

    void altaEspectaculo(EspectaculoDTO espectaculo);

    EspectaculoDTO getEspectaculoPorId(Long idEspectaculo);

    List<EspectaculoDTO> getEspectaculosPorIdArtista(Long idArtista);

    void altaFuncion(FuncionDTO funcion);

    FuncionDTO getFuncionPorId(Long idFuncion);
    
    List<EspectaculoDTO> getEspectaculosPorCategoriaPlataforma(Long idPlataforma, Long idCategoria);
    
    Boolean isUsuarioRegistradoEnFuncion(Long idFuncion, Long idUsuario);
    
    Boolean isFuncionCompleta(Long idFuncion);
    
    void registrarUsuarioEnFuncion(Long idFuncion, Long idUsuario, Date fechaRegistro, BigDecimal costo);

    InformacionCanjeTresPorUno getInfoCanjeTresPorUno(Long idUsuario);

    void canjeTresPorUno(CanjeTresPorUnoDTO ctpudto);

}
