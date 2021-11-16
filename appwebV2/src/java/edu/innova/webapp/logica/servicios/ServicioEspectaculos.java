package edu.innova.webapp.logica.servicios;

import edu.innova.webapp.dtos.CanjeTresPorUnoDTO;
import edu.innova.webapp.dtos.EspectaculoDTO;
import edu.innova.webapp.dtos.FuncionDTO;
import edu.innova.webapp.dtos.InformacionCanjeTresPorUnoDTO;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import uy.innova.ws.dtos.EspectaculosRequest;

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

    InformacionCanjeTresPorUnoDTO getInfoCanjeTresPorUno(Long idUsuario);

    void canjeTresPorUno(CanjeTresPorUnoDTO ctpudto);
    
    List<EspectaculoDTO> getTodosLosEspectaculosAceptados();
    
    void altaFuncionFavorita(Long idFuncion, Long idUsuario);
   
    void bajaFuncionFavorita(Long idFuncion, Long idUsuario);
    
    List<FuncionDTO> getFuncionFavoritasesDeUsuario(Long idUsuario);
    
    Boolean isFuncionFavoritaDelUsuario(Long idFuncion, Long idUsuario);
    
    void altaEspectaculoFavorito(Long idEspectaculo, Long idUsuario);
    
    void bajaEspectaculoFavorito(Long idEspectaculo, Long idUsuario);
   
    public List<EspectaculoDTO> getEspectaculosFavoritosDeUsuario(Long idUsuario);

    public Boolean isEspectaculoFavoritaDelUsuario(Long idEspectaculo, Long idUsuario);

    void finalizarEspectaculo(Long idEspectaculo);
}
