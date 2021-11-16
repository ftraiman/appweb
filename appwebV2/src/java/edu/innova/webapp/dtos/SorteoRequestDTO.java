package edu.innova.webapp.dtos;

import java.util.List;

public class SorteoRequestDTO {

    private List<Long> idUsuarios;
    private Long idFuncion;
    private String premio;

    public SorteoRequestDTO() {
    }

    public SorteoRequestDTO(List<Long> idUsuarios, Long idFuncion, String premio) {
        this.idUsuarios = idUsuarios;
        this.idFuncion = idFuncion;
        this.premio = premio;
    }

    public List<Long> getIdUsuarios() {
        return idUsuarios;
    }

    public void setIdUsuarios(List<Long> idUsuarios) {
        this.idUsuarios = idUsuarios;
    }

    public Long getIdFuncion() {
        return idFuncion;
    }

    public void setIdFuncion(Long idFuncion) {
        this.idFuncion = idFuncion;
    }

    public String getPremio() {
        return premio;
    }

    public void setPremio(String premio) {
        this.premio = premio;
    }
}
