package edu.innova.webapp.logica.servicios.impl;

import edu.innova.logica.controladores.EspectaculoControlador;
import edu.innova.logica.controladores.FuncionControlador;
import edu.innova.logica.controladores.impl.EspectaculosControladorImpl;
import edu.innova.logica.controladores.impl.FuncionControladorImpl;
import edu.innova.logica.dtos.UsuarioDTO;
import edu.innova.webapp.dtos.CanjeTresPorUnoDTO;
import edu.innova.webapp.dtos.CategoriaDTO;
import edu.innova.webapp.dtos.EspectaculoDTO;
import edu.innova.webapp.dtos.FuncionDTO;
import edu.innova.webapp.dtos.InformacionCanjePaqueteDTO;
import edu.innova.webapp.dtos.InformacionCanjeTresPorUno;
import edu.innova.webapp.dtos.PlataformaDTO;
import edu.innova.webapp.logica.servicios.ServicioEspectaculos;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class ServicioEspectaculosAppSwingImpl implements ServicioEspectaculos {

    private static ServicioEspectaculosAppSwingImpl instance;
    private static final EspectaculoControlador espectaculoControlador = new EspectaculosControladorImpl().getInstance();
    private static final FuncionControlador funcionControlador = new FuncionControladorImpl().getInstance();

    private ServicioEspectaculosAppSwingImpl() {
    }

    public static ServicioEspectaculosAppSwingImpl getInstance() {
        if (instance == null) {
            instance = new ServicioEspectaculosAppSwingImpl();
        }
        return instance;
    }

    @Override
    public void altaEspectaculo(EspectaculoDTO espectaculo) {
        espectaculoControlador.AltaEspectaculoDTO(espectaculoMapper(espectaculo));
    }

    @Override
    public List<EspectaculoDTO> getEspectaculosPorIdArtista(Long idArtista) {
        return espectaculoControlador.getTodosLosEspectaculosPorArtistaDTO(idArtista).stream().map(this::espectaculoMapper).collect(Collectors.toList());
    }

    @Override
    public EspectaculoDTO getEspectaculoPorId(Long idEspectaculo) {
        edu.innova.logica.dtos.EspectaculoDTO espectaculoDTO = espectaculoControlador.getEspectaculoPorIdDTO(idEspectaculo);
        if (espectaculoDTO != null) {
            return espectaculoMapper(espectaculoDTO);
        }
        return null;
    }

    @Override
    public void altaFuncion(FuncionDTO funcion) {
        funcionControlador.altaFuncionDTO(funcionMapper(funcion));
    }

    @Override
    public FuncionDTO getFuncionPorId(Long idFuncion) {
        return funcionMapper(funcionControlador.getFuncionDTOPorId(idFuncion));
    }

    @Override
    public List<EspectaculoDTO> getEspectaculosPorCategoriaPlataforma(Long idPlataforma, Long idCategoria) {
        List<CategoriaDTO> categorias = ServicioMenu.getCategoriasDTO();
        List<PlataformaDTO> plataformas = ServicioMenu.getPlataformas();

        List<edu.innova.logica.dtos.EspectaculoDTO> espectaculosDTO = espectaculoControlador.buscarEspectaculosDTO(idPlataforma, idCategoria);
        List<EspectaculoDTO> espectaculos = espectaculosDTO.stream().map(this::espectaculoMapper).collect(Collectors.toList());
        espectaculos.forEach(espectaculo -> completarParametros(espectaculo, categorias, plataformas));

        return espectaculos;
    }

    @Override
    public Boolean isUsuarioRegistradoEnFuncion(Long idFuncion, Long idUsuario) {
        return funcionControlador.getUsuarioRegistradoEnFuncion(idFuncion, idUsuario);
    }

    @Override
    public void registrarUsuarioEnFuncion(Long idFuncion, Long idUsuario, Date fechaRegistro, BigDecimal costo) {
        funcionControlador.altaEspectadorAFuncionDto(idFuncion, idUsuario, fechaRegistro, costo);
    }

    private edu.innova.logica.dtos.EspectaculoDTO espectaculoMapper(EspectaculoDTO e) {
        return new edu.innova.logica.dtos.EspectaculoDTO(e.getNombre(), e.getDescripcion(), e.getDuracion(), e.getEspectadoresMinimos(), e.getEspectadoresMaximos(),
                e.getUrl(), e.getCosto(), e.getFechaRegistro(), e.getIdCategoria(), e.getEstado(), e.getImagen(), e.getIdArtista(), e.getIdPlataforma());

    }

    private EspectaculoDTO espectaculoMapper(edu.innova.logica.dtos.EspectaculoDTO e) {
        EspectaculoDTO espectaculo = new EspectaculoDTO(e.getNombre(), e.getDescripcion(), e.getDuracion(), e.getEspectadoresMinimos(), e.getEspectadoresMaximos(),
                e.getUrl(), e.getCosto(), e.getFechaRegistro(), e.getIdCategoria(), e.getEstado(), e.getImagen(), e.getIdArtista(), e.getIdPlataforma());
        espectaculo.setId(e.getId());

        List<FuncionDTO> funciones = e.getFunciones()
                .stream()
                .map(ServicioEspectaculosAppSwingImpl::funcionMapper)
                .collect(Collectors.toList());
        espectaculo.setFunciones(funciones);
        return espectaculo;
    }

    private static edu.innova.logica.dtos.FuncionDTO funcionMapper(FuncionDTO funcion) {
        List<UsuarioDTO> artistasInvitados = funcion.getArtistasInvitados()
                .stream()
                .map(ServicioUsuariosAppSwingImpl::usuarioDtoMapper)
                .collect(Collectors.toList());
        
        return new edu.innova.logica.dtos.FuncionDTO(funcion.getNombre(), funcion.getIdEspectaculo(), funcion.getFechaInicio(),
                funcion.getFechaRegistro(), artistasInvitados, funcion.getImagen());
    }

    private static FuncionDTO funcionMapper(edu.innova.logica.dtos.FuncionDTO funcion) {
        List<edu.innova.webapp.dtos.UsuarioDTO> artistasInvitados = funcion.getArtistasInvitados()
                .stream()
                .map(ServicioUsuariosAppSwingImpl::usuarioDtoMapper)
                .collect(Collectors.toList());

        return new FuncionDTO(funcion.getId(), funcion.getNombre(), funcion.getIdEspectaculo(), funcion.getFechaInicio(),
                funcion.getFechaRegistro(), artistasInvitados, null);
    }

    private void completarParametros(EspectaculoDTO espectaculo, List<CategoriaDTO> categorias, List<PlataformaDTO> plataformas) {
        espectaculo.setPlataforma(plataformas.stream().filter(plataforma -> Objects.equals(plataforma.getId(), espectaculo.getIdPlataforma())).findFirst().get());
        espectaculo.setCategoria(categorias.stream().filter(categoria -> Objects.equals(categoria.getId(), espectaculo.getIdCategoria())).findFirst().get());
    }

    @Override
    public Boolean isFuncionCompleta(Long idFuncion) {
        return funcionControlador.isFuncionCompleta(idFuncion);
    }

    @Override
    public InformacionCanjeTresPorUno getInfoCanjeTresPorUno(Long idUsuario) {
        InformacionCanjeTresPorUno ictpu = new InformacionCanjeTresPorUno();
        List<FuncionDTO> funcionesParaCanjear
                = funcionControlador.getFuncionesDeUsuarioParaCanjear(idUsuario)
                        .stream()
                        .map(ServicioEspectaculosAppSwingImpl::funcionMapper)
                        .collect(Collectors.toList());
        ictpu.setIsCanjeDisponible((funcionesParaCanjear.size() >= 3));
        ictpu.setFuncionesCanjeables(funcionesParaCanjear);
        return ictpu;
    }

    @Override
    public void canjeTresPorUno(CanjeTresPorUnoDTO ctpudto) {
        funcionControlador.canjearFunciones(canjeTresPorUnoMapper(ctpudto));
    }
    
    @Override
    public List<EspectaculoDTO> getTodosLosEspectaculos()  {
        try {
            return espectaculoControlador.getTodosLosEspectaculosDTO()
                    .stream()
                    .filter(espectaculo -> "aceptado".equalsIgnoreCase(espectaculo.getEstado()))
                    .map(espectaculo -> espectaculoMapper(espectaculo))
                    .collect(Collectors.toList());
        } catch (SQLException ex) {   
        }
        return null;
    }

    private edu.innova.logica.dtos.CanjeTresPorUnoDTO canjeTresPorUnoMapper(CanjeTresPorUnoDTO ctpudto) {
        return new edu.innova.logica.dtos.CanjeTresPorUnoDTO(ctpudto.getId(), ctpudto.getIdUsuario(), ctpudto.getIdFuncionObtenida(),
                ctpudto.getFuncionesCanjeadas(), ctpudto.getFechaRegistro(), ctpudto.getIdEspectaculoDeFuncion());
    }

}
