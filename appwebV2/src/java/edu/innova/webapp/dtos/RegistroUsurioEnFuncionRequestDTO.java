package edu.innova.webapp.dtos;

import java.math.BigDecimal;
import java.util.Date;

public class RegistroUsurioEnFuncionRequestDTO {
    
    private Long idFuncion;
    private Long idUsuario;
    private Date fechaRegistro;
    private BigDecimal costo;

    public RegistroUsurioEnFuncionRequestDTO() {
    }

    public RegistroUsurioEnFuncionRequestDTO(Long idFuncion, Long idUsuario, Date fechaRegistro, BigDecimal costo) {
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
    
    
    
}
