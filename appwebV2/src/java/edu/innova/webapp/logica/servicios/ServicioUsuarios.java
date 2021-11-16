package edu.innova.webapp.logica.servicios;


import edu.innova.webapp.dtos.UsuarioDTO;
import java.util.List;


public interface ServicioUsuarios {
    
    boolean guardarNuevoUsuario(UsuarioDTO usuario);
    
    UsuarioDTO getUsuarioDTOPorCredenciales(String nickname, String email, String clave);
    
    List<UsuarioDTO> getTodosLosUsuarios();
    
    UsuarioDTO getUsuarioPorId(Long idUsuario);
    
    void modificarUsuarioDTO(UsuarioDTO usuario);
    
    List<UsuarioDTO> getUsuariosRegistradosALaFuncion(Long idFuncion);
    
    List<UsuarioDTO> getGanadoresDelSorteo(Long idFuncion, Integer premios);
    
    void registrarGanadores(Long idFuncion, List<Long> idUsuarios, String premioDeFuncion);
    
}
