package edu.innova.webapp.dtos;

import java.math.BigDecimal;
import java.util.Date;

public class FuncionRequestDTO {
    
    private String operacionFavorita;
    private Long idFuncion;
    private Long idUsuario;
    private Date fechaRegistro;
    private BigDecimal costo;

    public FuncionRequestDTO() {
    }

    public FuncionRequestDTO(Long idFuncion, Long idUsuario, Date fechaRegistro, BigDecimal costo) {
        this.idFuncion = idFuncion;
        this.idUsuario = idUsuario;
        this.fechaRegistro = fechaRegistro;
        this.costo = costo;
    }

    public Long getIdFuncion() {
        return idFuncion;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public BigDecimal getCosto() {
        return costo;
    }

    public void setIdFuncion(Long idFuncion) {
        this.idFuncion = idFuncion;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public void setCosto(BigDecimal costo) {
        this.costo = costo;
    }

    public String getOperacionFavorita() {
        return operacionFavorita;
    }

    public void setOperacionFavorita(String operacionFavorita) {
        this.operacionFavorita = operacionFavorita;
    }
}
