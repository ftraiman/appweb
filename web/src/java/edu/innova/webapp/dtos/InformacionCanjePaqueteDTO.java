package edu.innova.webapp.dtos;

import java.util.List;

public class InformacionCanjePaqueteDTO {

    private Boolean isCanjeDisponible;
    private List<PaqueteDTO> paquetesCanjeables;

    public Boolean getIsCanjeDisponible() {
        return isCanjeDisponible;
    }

    public void setIsCanjeDisponible(Boolean isCanjeDisponible) {
        this.isCanjeDisponible = isCanjeDisponible;
    }

    public List<PaqueteDTO> getPaquetesCanjeables() {
        return paquetesCanjeables;
    }

    public void setPaquetesCanjeables(List<PaqueteDTO> paquetesCanjeables) {
        this.paquetesCanjeables = paquetesCanjeables;
    }
}
