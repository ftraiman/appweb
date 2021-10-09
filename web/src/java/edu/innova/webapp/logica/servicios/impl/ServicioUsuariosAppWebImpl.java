package edu.innova.webapp.logica.servicios.impl;

import edu.innova.exceptions.InnovaModelException;
import edu.innova.logica.controladores.UsuarioControlador;
import edu.innova.logica.controladores.impl.UsuarioControladorImpl;

import edu.innova.webapp.dtos.UsuarioDTO;

import edu.innova.webapp.logica.servicios.ServicioUsuarios;

public class ServicioUsuariosAppWebImpl implements ServicioUsuarios {

    public static ServicioUsuariosAppWebImpl instance;

    private UsuarioControlador usuarioControlador = new UsuarioControladorImpl().getInstance();

    private ServicioUsuariosAppWebImpl() {
    }

    public static ServicioUsuariosAppWebImpl getInstance() {
        if (instance == null) {
            instance = new ServicioUsuariosAppWebImpl();
        }
        return instance;
    }

    @Override
    public boolean guardarNuevoUsuario(UsuarioDTO usuario) {
        try {
            usuarioControlador.crearUsuarioDTO(usuarioDtoMapper(usuario));
        } catch (InnovaModelException e) {
            throw new edu.innova.webapp.exceptions.InnovaModelException(e.getMessage(), e.getCause());
        }
        return true;
    }

    @Override
    public UsuarioDTO getUsuarioDTOPorCredenciales(String nickname, String email, String clave) {
        
        edu.innova.logica.dtos.UsuarioDTO u = usuarioControlador.getUsuarioDto(nickname, email, clave);
        if (u == null) {
            throw new edu.innova.webapp.exceptions.InnovaModelException("Usuario o contraseña incorrecta");
        }
        UsuarioDTO usuario = new UsuarioDTO(u.getId(), u.getTipo(), u.getNickname(), u.getNombre(), u.getApellido(), u.getEmail(), u.getFechaNacimiento(), u.getDescripcion(),
                u.getBiografia(), u.getLinkUsuario(), null, u.getImagen());
        return usuario;
    }

    private edu.innova.logica.dtos.UsuarioDTO usuarioDtoMapper(UsuarioDTO usuario) {
        return new edu.innova.logica.dtos.UsuarioDTO(usuario.getId(), usuario.getTipo(), usuario.getNickname(), usuario.getNombre(), usuario.getApellido(), usuario.getEmail(), usuario.getFechaNacimiento(),
                usuario.getDescripcion(), usuario.getBiografia(), usuario.getLinkUsuario(), usuario.getClave(), usuario.getImagen());
    }

}
