package edu.innova.webapp.dtos;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class EspectaculoDTO {

    private Long id;
    private Long idArtista;
    private String nombre;
    private Long idPlataforma;
    private PlataformaDTO plataforma;
    private String descripcion;
    private Integer duracion;
    private Integer espectadoresMinimos;
    private Integer espectadoresMaximos;
    private String url;
    private BigDecimal costo;
    private Date fechaRegistro;
    private List<FuncionDTO> funciones;
    private String estado;
    private Long idCategoria;
    private CategoriaDTO categoria;
    private String imagen;

    public EspectaculoDTO() {
    }

    public EspectaculoDTO(Long id, Long idArtista, String nombre, Long idPlataforma, String descripcion, Integer duracion, Integer espectadoresMinimos, Integer espectadoresMaximos, String url, BigDecimal costo, Date fechaRegistro, List<FuncionDTO> funciones, String estado, Long idCategoria, String imagen) {
        this.id = id;
        this.idArtista = idArtista;
        this.nombre = nombre;
        this.idPlataforma = idPlataforma;
        this.descripcion = descripcion;
        this.duracion = duracion;
        this.espectadoresMinimos = espectadoresMinimos;
        this.espectadoresMaximos = espectadoresMaximos;
        this.url = url;
        this.costo = costo;
        this.fechaRegistro = fechaRegistro;
        this.funciones = funciones;
        this.estado = estado;
        this.idCategoria = idCategoria;
        this.imagen = imagen;
    }
    
    public EspectaculoDTO(String nombre, String descripcion, Integer duracion, Integer espectadoredMinimos, Integer espectadoresMaximos, String url, BigDecimal costo, Date fechaRegistro,Long idCategoria, String estado,String imagen,Long idArtista, Long idPlataforma) {
        this.nombre = nombre;
        this.idArtista = idArtista;
        this.idPlataforma = idPlataforma;
        this.descripcion = descripcion;
        this.duracion = duracion;
        this.espectadoresMinimos = espectadoredMinimos;
        this.espectadoresMaximos = espectadoresMaximos;
        this.url = url;
        this.costo = costo;
        this.fechaRegistro = fechaRegistro;
        this.idCategoria = idCategoria;
        this.estado = estado;
        this.imagen = imagen;
    }
    //============================ CONSTRUCTURES =============================//

    //======================== GETTER & SETTER ===============================//
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdArtista() {
        return idArtista;
    }

    public void setIdArtista(Long idArtista) {
        this.idArtista = idArtista;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Long getIdPlataforma() {
        return idPlataforma;
    }

    public void setIdPlataforma(Long idPlataforma) {
        this.idPlataforma = idPlataforma;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getDuracion() {
        return duracion;
    }

    public void setDuracion(Integer duracion) {
        this.duracion = duracion;
    }

    public Integer getEspectadoresMinimos() {
        return espectadoresMinimos;
    }

    public void setEspectadoresMinimos(Integer espectadoresMinimos) {
        this.espectadoresMinimos = espectadoresMinimos;
    }

    public Integer getEspectadoresMaximos() {
        return espectadoresMaximos;
    }

    public void setEspectadoresMaximos(Integer espectadoresMaximos) {
        this.espectadoresMaximos = espectadoresMaximos;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public BigDecimal getCosto() {
        return costo;
    }

    public void setCosto(BigDecimal costo) {
        this.costo = costo;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public List<FuncionDTO> getFunciones() {
        return funciones;
    }

    public void setFunciones(List<FuncionDTO> funciones) {
        this.funciones = funciones;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Long getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(Long idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public PlataformaDTO getPlataforma() {
        return plataforma;
    }

    public void setPlataforma(PlataformaDTO plataforma) {
        this.plataforma = plataforma;
    }

    public CategoriaDTO getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoriaDTO categoria) {
        this.categoria = categoria;
    }
    
    
    //======================== GETTER & SETTER ===============================//

    @Override
    public String toString() {
        return String.format("%s %s", nombre, funciones);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final EspectaculoDTO other = (EspectaculoDTO) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

}
