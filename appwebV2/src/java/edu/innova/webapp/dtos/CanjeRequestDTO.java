package edu.innova.webapp.dtos;

public class CanjeRequestDTO {
    
    private Long idUsuario;
    private Long idPaquete;
    private Long idEspectaculo;

    public CanjeRequestDTO() {
    }

    public CanjeRequestDTO(Long idUsuario, Long idPaquete, Long idEspectaculo) {
        this.idUsuario = idUsuario;
        this.idPaquete = idPaquete;
        this.idEspectaculo = idEspectaculo;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Long getIdPaquete() {
        return idPaquete;
    }

    public void setIdPaquete(Long idPaquete) {
        this.idPaquete = idPaquete;
    }

    public Long getIdEspectaculo() {
        return idEspectaculo;
    }

    public void setIdEspectaculo(Long idEspectaculo) {
        this.idEspectaculo = idEspectaculo;
    }    
}
