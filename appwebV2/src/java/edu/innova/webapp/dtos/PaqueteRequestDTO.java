package edu.innova.webapp.dtos;

public class PaqueteRequestDTO {
    
    private Long idEspectaculo;
    private Long idPaquete;
    private Long idUsuario;
    private Long idFuncion;

    public PaqueteRequestDTO() {
    }

    public PaqueteRequestDTO(Long idEspectaculo, Long idPaquete) {
        this.idEspectaculo = idEspectaculo;
        this.idPaquete = idPaquete;
    }

    public Long getIdEspectaculo() {
        return idEspectaculo;
    }

    public Long getIdPaquete() {
        return idPaquete;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdEspectaculo(Long idEspectaculo) {
        this.idEspectaculo = idEspectaculo;
    }

    public void setIdPaquete(Long idPaquete) {
        this.idPaquete = idPaquete;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Long getIdFuncion() {
        return idFuncion;
    }

    public void setIdFuncion(Long idFuncion) {
        this.idFuncion = idFuncion;
    }
    
}
