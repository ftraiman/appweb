package edu.innova.webapp.dtos;

import java.util.Date;
import java.util.Objects;

public class UsuarioDTO {

    private Long id;
    private String clave;
    private String tipo;
    private String nickname;
    private String nombre;
    private String apellido;
    private String email;
    private Date fechaNacimiento;
    //TODO Agregar el campo nombre de foto
    private String imagen;

    private String descripcion;
    private String biografia;
    private String linkUsuario;

   
    public UsuarioDTO() {  
    }
    
    public UsuarioDTO(Long id, String tipo, String nickname, String nombre, String apellido, String email, Date fechaNacimiento, String descripcion, String biografia, String linkUsuario, String clave, String imagen) {
        this.id = id;
        this.tipo = tipo;
        this.imagen = imagen;
        this.clave = clave;
        this.nickname = nickname;
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.fechaNacimiento = fechaNacimiento;
        this.descripcion = descripcion;
        this.biografia = biografia;
        this.linkUsuario = linkUsuario;
    }

    public UsuarioDTO(String tipo, String nickname, String nombre, String apellido, String email, Date fechaNacimiento,String clave) {
        this.tipo = tipo;
        this.clave = clave;
        this.nickname = nickname;
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.fechaNacimiento = fechaNacimiento;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getBiografia() {
        return biografia;
    }

    public void setBiografia(String biografia) {
        this.biografia = biografia;
    }

    public String getLinkUsuario() {
        return linkUsuario;
    }

    public void setLinkUsuario(String linkUsuario) {
        this.linkUsuario = linkUsuario;
    }
    

   
    
    @Override
    public String toString() {
        return String.format("%s %s %s %s %s %s %s %s %s", tipo, nombre, apellido, nickname, email, fechaNacimiento, linkUsuario, biografia, descripcion);
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 47 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final UsuarioDTO other = (UsuarioDTO) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    
}
