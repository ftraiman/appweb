package edu.innova.webapp.dtos;

public class SeguidoresRequestDTO {
    
    private Long idUsuarioSeguidor; 
    private Long idUsuarioSeguido;

    public SeguidoresRequestDTO() {
    }

    public SeguidoresRequestDTO(Long idUsuarioSeguidor, Long idUsuarioSeguido) {
        this.idUsuarioSeguidor = idUsuarioSeguidor;
        this.idUsuarioSeguido = idUsuarioSeguido;
    }

    public Long getIdUsuarioSeguidor() {
        return idUsuarioSeguidor;
    }

    public Long getIdUsuarioSeguido() {
        return idUsuarioSeguido;
    }

    public void setIdUsuarioSeguidor(Long idUsuarioSeguidor) {
        this.idUsuarioSeguidor = idUsuarioSeguidor;
    }

    public void setIdUsuarioSeguido(Long idUsuarioSeguido) {
        this.idUsuarioSeguido = idUsuarioSeguido;
    }
    
}
