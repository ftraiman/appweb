package uy.innova.ws.dtos;

public class EspectaculosRequest {
    
   private String operacion;
   private Long idUsuario;
   private Long idEspectaculo;

    public EspectaculosRequest() {}

    public EspectaculosRequest(String operacion, Long idUsuario, Long idEspectaculo) {
        this.operacion = operacion;
        this.idUsuario = idUsuario;
        this.idEspectaculo = idEspectaculo;
    }
   
    public String getOperacion() {
        return operacion;
    }

    public void setOperacion(String operacion) {
        this.operacion = operacion;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Long getIdEspectaculo() {
        return idEspectaculo;
    }

    public void setIdEspectaculo(Long idEspectaculo) {
        this.idEspectaculo = idEspectaculo;
    }
    
}
