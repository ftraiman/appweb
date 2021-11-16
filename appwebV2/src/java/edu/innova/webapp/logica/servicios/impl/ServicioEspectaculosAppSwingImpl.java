package edu.innova.webapp.logica.servicios.impl;

//import edu.innova.logica.controladores.EspectaculoControlador;
//import edu.innova.logica.controladores.FuncionControlador;
//import edu.innova.logica.controladores.impl.EspectaculosControladorImpl;
//import edu.innova.logica.controladores.impl.FuncionControladorImpl;
//import edu.innova.logica.dtos.UsuarioDTO;
import edu.innova.webapp.dtos.CanjeTresPorUnoDTO;
import edu.innova.webapp.dtos.CategoriaDTO;
import edu.innova.webapp.dtos.EspectaculoDTO;
import edu.innova.webapp.dtos.FuncionDTO;
import edu.innova.webapp.dtos.InformacionCanjeTresPorUnoDTO;
import edu.innova.webapp.dtos.PlataformaDTO;
import edu.innova.webapp.dtos.FuncionRequestDTO;
import edu.innova.webapp.dtos.RespuestaDTO;
import edu.innova.webapp.logica.servicios.ServicioEspectaculos;
import edu.innova.webapp.rest.clientes.ClienteEspectaculos;
import edu.innova.webapp.rest.clientes.ClienteFunciones;
import edu.innova.webapp.rest.clientes.ClienteMiscelaneas;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import uy.innova.ws.dtos.EspectaculosRequest;

public class ServicioEspectaculosAppSwingImpl implements ServicioEspectaculos {

    private static ServicioEspectaculosAppSwingImpl instance;

    private static final ClienteEspectaculos clienteEspectaculos = ClienteEspectaculos.getInstance();
    private static final ClienteFunciones clienteFunciones = ClienteFunciones.getInstance();
    private static final ClienteMiscelaneas cm = ClienteMiscelaneas.getInstance();

//    private static final EspectaculoControlador espectaculoControlador = new EspectaculosControladorImpl().getInstance();
//    private static final FuncionControlador funcionControlador = new FuncionControladorImpl().getInstance();
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
        clienteEspectaculos.altaEspectaculo(espectaculo);

//        espectaculoControlador.AltaEspectaculoDTO(espectaculoMapper(espectaculo));
    }

    @Override
    public List<EspectaculoDTO> getEspectaculosPorIdArtista(Long idArtista) {
        return clienteEspectaculos.getEspectaculosPorArtista(idArtista);

//        return espectaculoControlador.getTodosLosEspectaculosPorArtistaDTO(idArtista).stream().map(this::espectaculoMapper).collect(Collectors.toList());
    }

    @Override
    public EspectaculoDTO getEspectaculoPorId(Long idEspectaculo) {
        EspectaculoDTO espectaculo = clienteEspectaculos.getEspectaculoPorId(idEspectaculo);
        return espectaculo.getNombre() != null ? espectaculo : null;

//        edu.innova.logica.dtos.EspectaculoDTO espectaculoDTO = espectaculoControlador.getEspectaculoPorIdDTO(idEspectaculo);
//        if (espectaculoDTO != null) {
//            return espectaculoMapper(espectaculoDTO);
//        }
//        return null;
    }

    @Override
    public void altaFuncion(FuncionDTO funcion) {
        clienteFunciones.altaFuncion(funcion);

//        funcionControlador.altaFuncionDTO(funcionMapper(funcion));
    }

    @Override
    public FuncionDTO getFuncionPorId(Long idFuncion) {
        return clienteFunciones.getFuncionPorId(idFuncion);

//        return funcionMapper(funcionControlador.getFuncionDTOPorId(idFuncion));
    }

    @Override
    public List<EspectaculoDTO> getEspectaculosPorCategoriaPlataforma(Long idPlataforma, Long idCategoria) {
        List<CategoriaDTO> categorias = cm.getTodosLasCategorias();
        List<PlataformaDTO> plataformas = cm.getTodosLasPlataformas();

        List<EspectaculoDTO> espectaculos = clienteEspectaculos.getEspectaculosPorPlataformaCategoria(idPlataforma, idCategoria);

//        List<CategoriaDTO> categorias = ServicioMenu.getCategoriasDTO();
//        List<PlataformaDTO> plataformas = ServicioMenu.getPlataformas();
//        List<edu.innova.logica.dtos.EspectaculoDTO> espectaculosDTO = espectaculoControlador.buscarEspectaculosDTO(idPlataforma, idCategoria);        
//        List<EspectaculoDTO> espectaculos = espectaculosDTO.stream().map(this::espectaculoMapper).collect(Collectors.toList());
        espectaculos.forEach(espectaculo -> completarParametros(espectaculo, categorias, plataformas));

        return espectaculos;
    }

    @Override
    public Boolean isUsuarioRegistradoEnFuncion(Long idFuncion, Long idUsuario) {
        RespuestaDTO respuesta = clienteFunciones.isUsuarioRegistradoEnFuncion(idFuncion, idUsuario);

        return respuesta.getConsulta();
//        return funcionControlador.getUsuarioRegistradoEnFuncion(idFuncion, idUsuario);
    }

    @Override
    public void registrarUsuarioEnFuncion(Long idFuncion, Long idUsuario, Date fechaRegistro, BigDecimal costo) {
        clienteFunciones.registroUsuarioEnFuncion(idFuncion, idUsuario, fechaRegistro, costo);

//        funcionControlador.altaEspectadorAFuncionDto(idFuncion, idUsuario, fechaRegistro, costo);
    }

    /*
    private edu.innova.logica.dtos.EspectaculoDTO espectaculoMapper(EspectaculoDTO e) {
        return new edu.innova.logica.dtos.EspectaculoDTO(e.getNombre(), e.getDescripcion(), e.getDuracion(), e.getEspectadoresMinimos(), e.getEspectadoresMaximos(),
                e.getUrl(), e.getCosto(), e.getFechaRegistro(), e.getIdCategoria(), e.getEstado(), e.getImagen(), e.getIdArtista(), e.getIdPlataforma());

    }
     */
//
//    private EspectaculoDTO espectaculoMapper(edu.innova.logica.dtos.EspectaculoDTO e) {
//        EspectaculoDTO espectaculo = new EspectaculoDTO(e.getNombre(), e.getDescripcion(), e.getDuracion(), e.getEspectadoresMinimos(), e.getEspectadoresMaximos(),
//                e.getUrl(), e.getCosto(), e.getFechaRegistro(), e.getIdCategoria(), e.getEstado(), e.getImagen(), e.getIdArtista(), e.getIdPlataforma());
//        espectaculo.setId(e.getId());
//
//        List<FuncionDTO> funciones = e.getFunciones()
//                .stream()
//                .map(ServicioEspectaculosAppSwingImpl::funcionMapper)
//                .collect(Collectors.toList());
//        espectaculo.setFunciones(funciones);
//        return espectaculo;
//    }
//    private static edu.innova.logica.dtos.FuncionDTO funcionMapper(FuncionDTO funcion) {
//        List<UsuarioDTO> artistasInvitados = funcion.getArtistasInvitados()
//                .stream()
//                .map(ServicioUsuariosAppSwingImpl::usuarioDtoMapper)
//                .collect(Collectors.toList());
//        
//        return new edu.innova.logica.dtos.FuncionDTO(funcion.getNombre(), funcion.getIdEspectaculo(), funcion.getFechaInicio(),
//                funcion.getFechaRegistro(), artistasInvitados, funcion.getImagen());
//    }
//    private static FuncionDTO funcionMapper(edu.innova.logica.dtos.FuncionDTO funcion) {
//        List<edu.innova.webapp.dtos.UsuarioDTO> artistasInvitados = funcion.getArtistasInvitados()
//                .stream()
//                .map(ServicioUsuariosAppSwingImpl::usuarioDtoMapper)
//                .collect(Collectors.toList());
//
//        return new FuncionDTO(funcion.getId(), funcion.getNombre(), funcion.getIdEspectaculo(), funcion.getFechaInicio(),
//                funcion.getFechaRegistro(), artistasInvitados, null);
//    }
    private void completarParametros(EspectaculoDTO espectaculo, List<CategoriaDTO> categorias, List<PlataformaDTO> plataformas) {
        espectaculo.setPlataforma(plataformas.stream().filter(plataforma -> Objects.equals(plataforma.getId(), espectaculo.getIdPlataforma())).findFirst().get());
        espectaculo.setCategoria(categorias.stream().filter(categoria -> Objects.equals(categoria.getId(), espectaculo.getIdCategoria())).findFirst().get());
    }

    @Override
    public Boolean isFuncionCompleta(Long idFuncion) {
        return clienteFunciones.isFuncionCompleta(idFuncion).getConsulta();

//        return funcionControlador.isFuncionCompleta(idFuncion);
    }

    @Override
    public InformacionCanjeTresPorUnoDTO getInfoCanjeTresPorUno(Long idUsuario) {
        InformacionCanjeTresPorUnoDTO ictpu = new InformacionCanjeTresPorUnoDTO();

        List<FuncionDTO> funcionesParaCanjear = clienteFunciones.getFuncionesDeUsuarioParaCanjear(idUsuario);

//        List<FuncionDTO> funcionesParaCanjear
//                = funcionControlador.getFuncionesDeUsuarioParaCanjear(idUsuario)
//                        .stream()
//                        .map(ServicioEspectaculosAppSwingImpl::funcionMapper)
//                        .collect(Collectors.toList());
        ictpu.setIsCanjeDisponible((funcionesParaCanjear.size() >= 3));
        ictpu.setFuncionesCanjeables(funcionesParaCanjear);
        return ictpu;
    }

    @Override
    public void canjeTresPorUno(CanjeTresPorUnoDTO ctpudto) {
        clienteFunciones.canjeTresPorUnoDTO(ctpudto);

//        funcionControlador.canjearFunciones(canjeTresPorUnoMapper(ctpudto));
    }

    @Override
    public List<EspectaculoDTO> getTodosLosEspectaculosAceptados() {
        return clienteEspectaculos.getTodosLosEspectaculos()
                .stream()
                .filter(espectaculo -> "aceptado".equalsIgnoreCase(espectaculo.getEstado()))
                .collect(Collectors.toList());

//        try {
//            return espectaculoControlador.getTodosLosEspectaculosDTO()
//                    .stream()
//                    .filter(espectaculo -> "aceptado".equalsIgnoreCase(espectaculo.getEstado()))
//                    .map(espectaculo -> espectaculoMapper(espectaculo))
//                    .collect(Collectors.toList());
//        } catch (SQLException ex) {   
//        }
//        return null;
    }

//    private edu.innova.logica.dtos.CanjeTresPorUnoDTO canjeTresPorUnoMapper(CanjeTresPorUnoDTO ctpudto) {
//        return new edu.innova.logica.dtos.CanjeTresPorUnoDTO(ctpudto.getId(), ctpudto.getIdUsuario(), ctpudto.getIdFuncionObtenida(),
//                ctpudto.getFuncionesCanjeadas(), ctpudto.getFechaRegistro(), ctpudto.getIdEspectaculoDeFuncion());
//    }
    @Override
    public void altaFuncionFavorita(Long idFuncion, Long idUsuario) {
        FuncionRequestDTO entidad = new FuncionRequestDTO();
        entidad.setOperacionFavorita("alta");
        entidad.setIdUsuario(idUsuario);
        entidad.setIdFuncion(idFuncion);
        clienteFunciones.altaFuncionFavorita(entidad);
    }

    @Override
    public void bajaFuncionFavorita(Long idFuncion, Long idUsuario) {
        FuncionRequestDTO entidad = new FuncionRequestDTO();
        entidad.setOperacionFavorita("baja");
        entidad.setIdUsuario(idUsuario);
        entidad.setIdFuncion(idFuncion);
        clienteFunciones.bajaFuncionFavorita(entidad);

    }

    @Override
    public List<FuncionDTO> getFuncionFavoritasesDeUsuario(Long idUsuario) {
        return clienteFunciones.getFuncionFavoritasesDeUsuario(idUsuario);
    }

    @Override
    public Boolean isFuncionFavoritaDelUsuario(Long idFuncion, Long idUsuario) {
        List<FuncionDTO> funcionesDelUsuario = clienteFunciones.getFuncionFavoritasesDeUsuario(idUsuario);
        return funcionesDelUsuario.stream().anyMatch(funcion -> funcion.getId() == idFuncion);
    }

    @Override
    public void altaEspectaculoFavorito(Long idEspectaculo, Long idUsuario) {
        EspectaculosRequest entidad = new EspectaculosRequest("alta", idUsuario, idEspectaculo);
        clienteEspectaculos.favoritos(entidad);
    }

    @Override
    public void bajaEspectaculoFavorito(Long idEspectaculo, Long idUsuario) {
        EspectaculosRequest entidad = new EspectaculosRequest("baja", idUsuario, idEspectaculo);
        clienteEspectaculos.favoritos(entidad);

    }

    @Override
    public List<EspectaculoDTO> getEspectaculosFavoritosDeUsuario(Long idUsuario) {
        return clienteEspectaculos.getEspectaculosFavoritosPorUsuario(idUsuario);
    }

    @Override
    public Boolean isEspectaculoFavoritaDelUsuario(Long idEspectaculo, Long idUsuario) {
        List<EspectaculoDTO> espectaculosDelUsuario = clienteEspectaculos.getEspectaculosFavoritosPorUsuario(idUsuario);
        return espectaculosDelUsuario.stream().anyMatch(e -> e.getId() == idEspectaculo);
    }
    
    @Override
    public void finalizarEspectaculo(Long idEspectaculo) {
        EspectaculosRequest request = new EspectaculosRequest();
        request.setIdEspectaculo(idEspectaculo);
        clienteEspectaculos.finalizar(request);
    }

}
