package edu.innova.webapp.dtos;

public class InformacionFuncionDTO {
    
    private FuncionDTO funcion;
    private EspectaculoDTO espectaculo;
    private UsuarioDTO artista;
    
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
    
}
