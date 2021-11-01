package edu.innova.webapp.dtos;

import java.util.List;

public class InformacionCanjeTresPorUno {

    private Boolean isCanjeDisponible;
    private List<FuncionDTO> funcionesCanjeables;

    public Boolean getIsCanjeDisponible() {
        return isCanjeDisponible;
    }

    public void setIsCanjeDisponible(Boolean isCanjeDisponible) {
        this.isCanjeDisponible = isCanjeDisponible;
    }

    public List<FuncionDTO> getFuncionesCanjeables() {
        return funcionesCanjeables;
    }

    public void setFuncionesCanjeables(List<FuncionDTO> funcionesCanjeables) {
        this.funcionesCanjeables = funcionesCanjeables;
    }
    
}
