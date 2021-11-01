package edu.innova.webapp.dtos;

import java.util.List;

public class InformacionEspectaculoDTO {
    
    private EspectaculoDTO espectaculo;
    private UsuarioDTO usuario;
    private CategoriaDTO categoria;
    private PlataformaDTO plataforma;
    private List<PaqueteDTO> paquetes;

    public EspectaculoDTO getEspectaculo() {
        return espectaculo;
    }

    public void setEspectaculo(EspectaculoDTO espectaculo) {
        this.espectaculo = espectaculo;
    }

    public UsuarioDTO getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioDTO usuario) {
        this.usuario = usuario;
    }

    public CategoriaDTO getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoriaDTO categoria) {
        this.categoria = categoria;
    }

    public PlataformaDTO getPlataforma() {
        return plataforma;
    }

    public void setPlataforma(PlataformaDTO plataforma) {
        this.plataforma = plataforma;
    }

    public List<PaqueteDTO> getPaquetes() {
        return paquetes;
    }

    public void setPaquetes(List<PaqueteDTO> paquetes) {
        this.paquetes = paquetes;
    }    
}
