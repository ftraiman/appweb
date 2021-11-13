package edu.innova.webapp.logica.servicios.impl;

//import edu.innova.logica.controladores.CanjeControlador;
//import edu.innova.logica.controladores.EspectaculoControlador;
//import edu.innova.logica.controladores.FuncionControlador;
//import edu.innova.logica.controladores.PaqueteControlador;
//import edu.innova.logica.controladores.impl.CanjeControladorImpl;
//import edu.innova.logica.controladores.impl.EspectaculosControladorImpl;
//import edu.innova.logica.controladores.impl.FuncionControladorImpl;
//import edu.innova.logica.controladores.impl.PaqueteControladorImpl;
import edu.innova.webapp.dtos.EspectaculoDTO;
import edu.innova.webapp.dtos.InformacionCanjePaqueteDTO;
import edu.innova.webapp.dtos.PaqueteDTO;
import edu.innova.webapp.logica.servicios.ServicioPaquetes;
import edu.innova.webapp.rest.clientes.ClienteCanjes;
import edu.innova.webapp.rest.clientes.ClienteEspectaculos;
import edu.innova.webapp.rest.clientes.ClienteFunciones;
import edu.innova.webapp.rest.clientes.ClientePaquetes;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class ServicioPaquetesAppSwingImpl implements ServicioPaquetes {

    private static ServicioPaquetesAppSwingImpl instance;
    
    private static final ClienteEspectaculos clienteEspectaculos = ClienteEspectaculos.getInstance();
    private static final ClienteFunciones clienteFunciones = ClienteFunciones.getInstance();
    private static final ClientePaquetes clientePaquetes = ClientePaquetes.getInstance();
    private static final ClienteCanjes clienteCanjes = ClienteCanjes.getInstance();
    
//    private static final EspectaculoControlador espectaculoControlador = new EspectaculosControladorImpl().getInstance();
//    private static final FuncionControlador funcionControlador = new FuncionControladorImpl().getInstance();
//    private static final PaqueteControlador paqueteControlador = new PaqueteControladorImpl().getInstance();
//    private static final CanjeControlador canjeControlador = CanjeControladorImpl.getInstance();
//    private static final ServicioEspectaculos servicioEspectaculos = ServicioEspectaculosAppSwingImpl.getInstance();

    private ServicioPaquetesAppSwingImpl() {
    }

    public static ServicioPaquetesAppSwingImpl getInstance() {
        if (instance == null) {
            instance = new ServicioPaquetesAppSwingImpl();
        }
        return instance;
    }

    @Override
    public void altaPaquete(PaqueteDTO paquete, Long idArtista) {
        clientePaquetes.altaPaquete(paquete);
        
//        paqueteControlador.altaPaqueteDTO(paqueteDTOMapper(paquete), idArtista);
    }

    @Override
    public List<PaqueteDTO> getPaquetesPorIdArtista(Long idArtista) {
        return clientePaquetes.getPaquetePorIdArtista(idArtista);
        
//        List<PaqueteDTO> paquetes = paqueteControlador.getPaquetesDTOPorIdArtista(idArtista)
//                .stream()
//                .map(paquete -> this.paqueteDTOMapper(paquete))
//                .collect(Collectors.toList());
//        return paquetes;
    }

    @Override
    public PaqueteDTO getPaquetePorId(Long idPaquete) {
        return clientePaquetes.getPaquetePorId(idPaquete);

//        return paqueteDTOMapper(paqueteControlador.getPaqueteDTOporId(idPaquete));
    }

    @Override
    public List<EspectaculoDTO> getEspectaculosNoIncluidosEnPaquete(Long idUsuario, Long idPaquete) {
        
        return clientePaquetes.getEspectaculosNoIncluidosEnPaquete(idUsuario, idPaquete);
        
//        return espectaculoControlador.getEspectaculosDTONoIncluidosEnPaquete(idUsuario, idPaquete)
//                .stream()
//                .map(e -> new EspectaculoDTO(e.getId(), e.getIdArtista(), e.getNombre(), e.getIdPlataforma(), e.getDescripcion(), e.getDuracion(), e.getEspectadoresMinimos(),
//                e.getEspectadoresMaximos(), e.getUrl(), e.getCosto(), e.getFechaRegistro(), new ArrayList<FuncionDTO>(), e.getEstado(), e.getIdCategoria(), e.getImagen()))
//                .collect(Collectors.toList());
    }

    @Override
    public void altaEspectaculoEnPaquete(Long idEspectaculo, Long idPaquete) {
        clientePaquetes.altaEspectaculoEnPaquete(idEspectaculo, idPaquete);
        
//        paqueteControlador.altaPaqueteDTOEspectaculo(idPaquete, idEspectaculo);
    }

    @Override
    public void altaUsuarioEnPaquete(Long idUsuario, Long idPaquete) {
        clientePaquetes.altaUsuarioEnPaquete(idUsuario, idPaquete);
        
//        paqueteControlador.altaUsuarioEnPaquete(idUsuario, idPaquete);
    }

    @Override
    public List<PaqueteDTO> getPaquetesPorIdEspectaculo(Long idEspectaculo) {
        return clientePaquetes.getPaquetesPorIdEspectaculo(idEspectaculo);
        
//        return paqueteControlador.getPaqueteDTOPorIdEspectaculo(idEspectaculo)
//                .stream()
//                .map(this::paqueteDTOMapper)
//                .collect(Collectors.toList());
    }

    @Override
    public List<PaqueteDTO> getPaquetesCompradosPorIdUsuario(Long idUsuario) {
        return clientePaquetes.getPaquetesCompradosPorIdUsuario(idUsuario);

//        return paqueteControlador.getPaquetesContratadosPorIdUsuario(idUsuario)
//                .stream()
//                .map(this::paqueteDTOMapper)
//                .collect(Collectors.toList());
    }

    @Override
    public List<PaqueteDTO> getTodosLosPaquetes() {
        return clientePaquetes.getTodosLosPaquetes();

//        return paqueteControlador.getTodosLosPaquetesDTO()
//                .stream()
//                .map(this::paqueteDTOMapper)
//                .collect(Collectors.toList());
    }

//    private edu.innova.logica.dtos.PaqueteDTO paqueteDTOMapper(PaqueteDTO paquete) {
//        edu.innova.logica.dtos.PaqueteDTO paqueteDto = new edu.innova.logica.dtos.PaqueteDTO();
//        paqueteDto.setDescripcion(paquete.getDescripcion());
//        paqueteDto.setDescuento(paquete.getDescuento());
//        paqueteDto.setEspectaculos(new ArrayList<>());
//        paqueteDto.setFechaFin(paquete.getFechaFin());
//        paqueteDto.setFechaInicio(paquete.getFechaInicio());
//        paqueteDto.setNombre(paquete.getNombre());
//        paqueteDto.setImagen(paquete.getImagen());
//        return paqueteDto;
//    }

//    private PaqueteDTO paqueteDTOMapper(edu.innova.logica.dtos.PaqueteDTO paquete) {
//        PaqueteDTO paqueteDto = new PaqueteDTO();
//        List<EspectaculoDTO> espectaculos = paquete.getEspectaculos()
//                .stream()
//                .map(e -> new EspectaculoDTO(e.getId(), e.getIdArtista(), e.getNombre(), e.getIdPlataforma(), e.getDescripcion(), e.getDuracion(), e.getEspectadoresMinimos(),
//                e.getEspectadoresMaximos(), e.getUrl(), e.getCosto(), e.getFechaRegistro(), new ArrayList<FuncionDTO>(), e.getEstado(), e.getIdCategoria(), e.getImagen()))
//                .collect(Collectors.toList());
//        paqueteDto.setEspectaculos(espectaculos);
//        paqueteDto.setId(paquete.getId());
//        paqueteDto.setDescripcion(paquete.getDescripcion());
//        paqueteDto.setDescuento(paquete.getDescuento());
//        paqueteDto.setFechaFin(paquete.getFechaFin());
//        paqueteDto.setFechaInicio(paquete.getFechaInicio());
//        paqueteDto.setNombre(paquete.getNombre());
//        paqueteDto.setImagen(paquete.getImagen());
//        return paqueteDto;
//    }

    @Override
    public InformacionCanjePaqueteDTO getInfoCanjePaquete(Long idUsuario, Long idFuncion) {
        InformacionCanjePaqueteDTO icpdto = new InformacionCanjePaqueteDTO();
        
        List<PaqueteDTO> paquetes = clientePaquetes.getPaquetesConLaFuncion(idUsuario, idFuncion);
        
//        List<edu.innova.logica.dtos.PaqueteDTO> paquetes = paqueteControlador.getPaquetesConLaFuncion(idUsuario, idFuncion);
        
        icpdto.setIsCanjeDisponible(paquetes.size() > 0);
        icpdto.setPaquetesCanjeables(
                paquetes
//                .stream()
//                .map(this::paqueteDTOMapper)
//                .collect(Collectors.toList())
        );
        return icpdto;
    }

    @Override
    public void canjePaquete(Long idUsuario, Long idFuncion, PaqueteDTO paquete, EspectaculoDTO espectaculo) {
        BigDecimal costo = espectaculo.getCosto().multiply(BigDecimal.valueOf(100).subtract(paquete.getDescuento())).divide(BigDecimal.valueOf(100));
        
        clienteFunciones.registroUsuarioEnFuncion(idFuncion, idUsuario, new Date(), costo);
        clienteCanjes.altaEspectaculoPaqueteUtilizado(idUsuario, idFuncion, idUsuario);

//        servicioEspectaculos.registrarUsuarioEnFuncion(idFuncion, idUsuario, new Date(), costo);
//        canjeControlador.altaEspectaculoPaqueteUtilizado(idUsuario, paquete.getId(), espectaculo.getId());
    }
}
