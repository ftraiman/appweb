package edu.innova.webapp.dtos;

public class RespuestaDTO {

    private String mensaje;
    private Boolean consulta;

    public RespuestaDTO() {
    }

    public RespuestaDTO(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public Boolean getConsulta() {
        return consulta;
    }

    public void setConsulta(Boolean consulta) {
        this.consulta = consulta;
    }
}
