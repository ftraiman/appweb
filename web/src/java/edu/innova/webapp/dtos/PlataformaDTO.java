package edu.innova.webapp.dtos;


public class PlataformaDTO {

    private Long id;
    private String nombre;
    private String descripcion;
    private String url;

    public PlataformaDTO() {
    }

    public PlataformaDTO(Long id, String nombre, String descripcion, String url) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.url = url;
    }

    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getUrl() {
        return url;
    }

}
