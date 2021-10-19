package edu.innova.webapp.dtos;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PaqueteDTO {

    private Long id;
    private Long idArtista;
    private String nombre;
    private String descripcion;
    private Date fechaInicio;
    private Date fechaFin;
    private BigDecimal descuento;
    private List<EspectaculoDTO> espectaculos = new ArrayList<>();
    private String imagen;

    public PaqueteDTO() {
    }

    public PaqueteDTO(Long idArtista, String nombre, String descripcion, Date fechaInicio, Date fechaFin, BigDecimal descuento, String imagen) {
        this.idArtista = idArtista;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.descuento = descuento;
        this.imagen = imagen;
    }

    public PaqueteDTO(Long id, Long idArtista, String nombre, String descripcion, Date fechaInicio, Date fechaFin, BigDecimal descuento, String imagen) {
        this.id = id;
        this.idArtista = idArtista;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.descuento = descuento;
        this.imagen = imagen;
    }
   
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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public BigDecimal getDescuento() {
        return descuento;
    }

    public void setDescuento(BigDecimal descuento) {
        this.descuento = descuento;
    }

    public List<EspectaculoDTO> getEspectaculos() {
        return espectaculos;
    }

    public void setEspectaculos(List<EspectaculoDTO> espectaculos) {
        this.espectaculos = espectaculos;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    
}
