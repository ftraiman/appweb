package edu.innova.webapp.dtos;

import java.util.ArrayList;
import java.util.List;

public class EspectaculosDeUsuario {
    
    private List<EspectaculoDTO> espectaculosIngresados = new ArrayList<>();
    private List<EspectaculoDTO> espectaculosAceptados = new ArrayList<>();
    private List<EspectaculoDTO> espectaculosRechazados = new ArrayList<>();

    public List<EspectaculoDTO> getEspectaculosIngresados() {
        return espectaculosIngresados;
    }

    public void setEspectaculosIngresados(List<EspectaculoDTO> espectaculosIngresados) {
        this.espectaculosIngresados = espectaculosIngresados;
    }

    public List<EspectaculoDTO> getEspectaculosAceptados() {
        return espectaculosAceptados;
    }

    public void setEspectaculosAceptados(List<EspectaculoDTO> espectaculosAceptados) {
        this.espectaculosAceptados = espectaculosAceptados;
    }

    public List<EspectaculoDTO> getEspectaculosRechazados() {
        return espectaculosRechazados;
    }

    public void setEspectaculosRechazados(List<EspectaculoDTO> espectaculosRechazados) {
        this.espectaculosRechazados = espectaculosRechazados;
    }
    
    
}
