package edu.innova.webapp.logica.servicios;

import edu.innova.webapp.dtos.UsuarioDTO;
import java.util.List;


public interface ServicioSeguidores {
    
    List<UsuarioDTO> getUsuariosQueSigue(Long idUsuarioSeguidor);

    List<UsuarioDTO> getUsuariosQueLoSiguen(Long idUsuarioSeguido);
    
    boolean seguirUsuario(Long idUsuarioSeguidor, Long idUsuarioSeguido);
            
    boolean dejarDeSeguiUsuario(Long idUsuarioSeguidor, Long idUsuarioSeguido);
       
}
