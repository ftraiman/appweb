package edu.innova.webapp.logica.servicios;


import edu.innova.webapp.dtos.UsuarioDTO;


public interface ServicioUsuarios {
    
    boolean guardarNuevoUsuario(UsuarioDTO usuario);
    
    UsuarioDTO getUsuarioDTOPorCredenciales(String nickname, String email, String clave);
    
}
