package edu.innova.webapp.dtos;

public class InformacionFuncionDTO {
    
    private FuncionDTO funcion;
    private EspectaculoDTO espectaculo;
    private UsuarioDTO artista;
    private Boolean isUsuarioRegistradoEnFuncion;
    private Boolean isUsuarioLogueado;
    private Boolean isFuncionCompleta;
    
    public FuncionDTO getFuncion() {
        return funcion;
    }

    public void setFuncion(FuncionDTO funcion) {
        this.funcion = funcion;
    }

    public EspectaculoDTO getEspectaculo() {
        return espectaculo;
    }

    public void setEspectaculo(EspectaculoDTO espectaculo) {
        this.espectaculo = espectaculo;
    }

    public UsuarioDTO getArtista() {
        return artista;
    }

    public void setArtista(UsuarioDTO artista) {
        this.artista = artista;
    }

    public Boolean getIsUsuarioRegistradoEnFuncion() {
        return isUsuarioRegistradoEnFuncion;
    }

    public void setIsUsuarioRegistradoEnFuncion(Boolean isUsuarioRegistradoEnFuncion) {
        this.isUsuarioRegistradoEnFuncion = isUsuarioRegistradoEnFuncion;
    }

    public Boolean getIsUsuarioLogueado() {
        return isUsuarioLogueado;
    }

    public void setIsUsuarioLogueado(Boolean isUsuarioLogueado) {
        this.isUsuarioLogueado = isUsuarioLogueado;
    }

    public Boolean getIsFuncionCompleta() {
        return isFuncionCompleta;
    }

    public void setIsFuncionCompleta(Boolean isFuncionCompleta) {
        this.isFuncionCompleta = isFuncionCompleta;
    }
}
