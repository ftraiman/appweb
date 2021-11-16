package edu.innova.webapp.dtos;

public class ResultadosSorteosDTO {
    
    private String nombreFuncion;
    private String premio;

    public ResultadosSorteosDTO() {
    }

    public ResultadosSorteosDTO(String nombreFuncion, String premio) {
        this.nombreFuncion = nombreFuncion;
        this.premio = premio;
    }
    
    public String getNombreFuncion() {
        return nombreFuncion;
    }

    public void setNombreFuncion(String nombreFuncion) {
        this.nombreFuncion = nombreFuncion;
    }

    public String getPremio() {
        return premio;
    }

    public void setPremio(String premio) {
        this.premio = premio;
    }
    
}
