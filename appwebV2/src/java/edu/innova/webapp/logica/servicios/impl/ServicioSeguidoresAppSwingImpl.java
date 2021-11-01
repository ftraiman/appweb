package edu.innova.webapp.logica.servicios.impl;

import edu.innova.logica.controladores.UsuarioControlador;
import edu.innova.logica.controladores.impl.UsuarioControladorImpl;
import edu.innova.webapp.dtos.UsuarioDTO;
import edu.innova.webapp.logica.servicios.ServicioSeguidores;
import java.util.ArrayList;
import java.util.List;

public class ServicioSeguidoresAppSwingImpl implements ServicioSeguidores {

    private static ServicioSeguidoresAppSwingImpl instance;

    private final UsuarioControlador usuarioControlador = new UsuarioControladorImpl().getInstance();

    private ServicioSeguidoresAppSwingImpl() {
    }

    public static ServicioSeguidoresAppSwingImpl getInstance() {
        if (instance == null) {
            instance = new ServicioSeguidoresAppSwingImpl();
        }
        return instance;
    }

    @Override
    public List<UsuarioDTO> getUsuariosQueSigue(Long idUsuarioSeguidor) {
        List<edu.innova.logica.dtos.UsuarioDTO> usuariosSeguidosSwing = usuarioControlador.usuariosQueSigue(idUsuarioSeguidor);
        return mapperUsuarios(usuariosSeguidosSwing);

    }

    @Override
    public List<UsuarioDTO> getUsuariosQueLoSiguen(Long idUsuarioSeguido) {
        List<edu.innova.logica.dtos.UsuarioDTO> usuariosSeguidosSwing = usuarioControlador.usuariosQueLoSiguen(idUsuarioSeguido);
        return mapperUsuarios(usuariosSeguidosSwing);
    }
    
    public boolean seguirUsuario(Long idUsuarioSeguidor, Long idUsuarioSeguido) {
        usuarioControlador.seguirUsuario(idUsuarioSeguidor, idUsuarioSeguido);
        return true;
    }
    
    @Override
    public boolean dejarDeSeguiUsuario(Long idUsuarioSeguidor, Long idUsuarioSeguido) {
        usuarioControlador.dejarDeSeguir(idUsuarioSeguidor, idUsuarioSeguido);
        return true;
    }

    private List<UsuarioDTO> mapperUsuarios(List<edu.innova.logica.dtos.UsuarioDTO> usuariosSeguidosSwing) {
        List<UsuarioDTO> usuariosSeguidos = new ArrayList<>();
        if (usuariosSeguidosSwing != null && usuariosSeguidosSwing.size() > 0) {
            usuariosSeguidosSwing.forEach(u -> usuariosSeguidos.add(new UsuarioDTO(u.getId(), u.getTipo(), u.getNickname(), u.getNombre(), u.getApellido(), u.getImagen())));
        }
        return usuariosSeguidos;
    }

}
