package edu.innova.webapp.logica.servicios.impl;

//import edu.innova.exceptions.InnovaModelException;
//import edu.innova.logica.controladores.UsuarioControlador;
//import edu.innova.logica.controladores.impl.UsuarioControladorImpl;

import edu.innova.webapp.dtos.ResultadosSorteosDTO;
import edu.innova.webapp.dtos.SorteoRequestDTO;
import edu.innova.webapp.dtos.UsuarioDTO;

import edu.innova.webapp.logica.servicios.ServicioUsuarios;
import edu.innova.webapp.rest.clientes.ClienteUsuarios;
import java.util.List;

public class ServicioUsuariosAppSwingImpl implements ServicioUsuarios {

    private static ServicioUsuariosAppSwingImpl instance;

//    private UsuarioControlador usuarioControlador = new UsuarioControladorImpl().getInstance();
    private ClienteUsuarios clienteUsuarios = ClienteUsuarios.getInstance();

    private ServicioUsuariosAppSwingImpl() {
    }

    public static ServicioUsuariosAppSwingImpl getInstance() {
        if (instance == null) {
            instance = new ServicioUsuariosAppSwingImpl();
        }
        return instance;
    }

    @Override
    public boolean guardarNuevoUsuario(UsuarioDTO usuario) {
        clienteUsuarios.altaUsuario(usuario);
        
//        try {
//            usuarioControlador.crearUsuarioDTO(usuarioDtoMapper(usuario));
//        } catch (InnovaModelException e) {
//            throw new edu.innova.webapp.exceptions.InnovaModelException(e.getMessage(), e.getCause());
//        }
        return true;
    }

    @Override
    public UsuarioDTO getUsuarioDTOPorCredenciales(String nickname, String email, String clave) {
        UsuarioDTO usuarioLogin = new UsuarioDTO();
        usuarioLogin.setNickname(nickname);
        usuarioLogin.setEmail(email);
        usuarioLogin.setClave(clave);
        UsuarioDTO usuario = clienteUsuarios.login(usuarioLogin);
        
//        edu.innova.logica.dtos.UsuarioDTO u = usuarioControlador.getUsuarioDto(nickname, email, clave);
//        if (u == null) {
//            throw new edu.innova.webapp.exceptions.InnovaModelException("Usuario o contraseña incorrecta");
//        }
//        UsuarioDTO usuario = new UsuarioDTO(u.getId(), u.getTipo(), u.getNickname(), u.getNombre(), u.getApellido(), u.getEmail(), u.getFechaNacimiento(), u.getDescripcion(),
//                u.getBiografia(), u.getLinkUsuario(), null, u.getImagen());
        return usuario;
    }

    @Override
    public List<UsuarioDTO> getTodosLosUsuarios() {
        return clienteUsuarios.getTodosLosUsuarios();
        
//        return usuarioControlador.getTodosLosUsuarioDTO()
//                .stream()
//                .map(u -> usuarioDtoMapper(u))
//                .collect(Collectors.toList());

    }
    
    @Override
    public UsuarioDTO getUsuarioPorId(Long idUsuario) {
        UsuarioDTO usuario = clienteUsuarios.getUsuarioPorId(idUsuario);
        return usuario.getNickname() != null ? usuario : null;

//        edu.innova.logica.dtos.UsuarioDTO usuarioDTO = usuarioControlador.getUsuarioDTOPorId(idUsuario);
//        if (usuarioDTO != null) {
//            return usuarioDtoMapper(usuarioDTO);
//        }
//        return null;
    }
    
    @Override
    public void modificarUsuarioDTO(UsuarioDTO usuario) {
        clienteUsuarios.modificarUsuario(usuario);
        
//        usuarioControlador.modificarUsuarioDTO(usuarioDtoMapper(usuario));
    }
    
    @Override
    public List<UsuarioDTO> getUsuariosRegistradosALaFuncion(Long idFuncion) {
        return clienteUsuarios.getEspectadoresDeFuncionPorUsuario(idFuncion);
    }
    
    @Override
    public List<UsuarioDTO> getGanadoresDelSorteo(Long idFuncion, Integer premios) {
        return clienteUsuarios.getGanadoresDelSorteo(idFuncion, premios);
    }
    
    @Override
    public void registrarGanadores(Long idFuncion, List<Long> idUsuarios, String premioDeFuncion) {
        SorteoRequestDTO request = new SorteoRequestDTO(idUsuarios, idFuncion, premioDeFuncion);
        clienteUsuarios.altaGanadoresSorteo(request);
    }
    
    @Override
    public List<ResultadosSorteosDTO> getSorteosGanados(String email) {
        return clienteUsuarios.getPremiosGanadosDelEspectador(email);
    }
    

//    public static edu.innova.logica.dtos.UsuarioDTO usuarioDtoMapper(UsuarioDTO usuario) {
//        return new edu.innova.logica.dtos.UsuarioDTO(usuario.getId(), usuario.getTipo(), usuario.getNickname(), usuario.getNombre(), usuario.getApellido(), usuario.getEmail(), usuario.getFechaNacimiento(),
//                usuario.getDescripcion(), usuario.getBiografia(), usuario.getLinkUsuario(), usuario.getClave(), usuario.getImagen());
//    }
//
//    public static UsuarioDTO usuarioDtoMapper(edu.innova.logica.dtos.UsuarioDTO usuario) {
//        return new UsuarioDTO(usuario.getId(), usuario.getTipo(), usuario.getNickname(), usuario.getNombre(), usuario.getApellido(), usuario.getEmail(), usuario.getFechaNacimiento(),
//                usuario.getDescripcion(), usuario.getBiografia(), usuario.getLinkUsuario(), usuario.getClave(), usuario.getImagen());
//    }
}
