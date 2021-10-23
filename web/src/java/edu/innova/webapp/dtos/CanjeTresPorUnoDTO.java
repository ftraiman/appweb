package edu.innova.webapp.dtos;

import java.util.Date;

public class CanjeTresPorUnoDTO {
    
    private Long id;
    private Long idUsuario;
    private Long idFuncionObtenida;
    private Long idEspectaculoDeFuncion;
    private String funcionesCanjeadas;
    private Date fechaRegistro;

    public CanjeTresPorUnoDTO() {
    }

    public CanjeTresPorUnoDTO(Long idUsuario, Long idFuncionObtenida, String funcionesCanjeadas, Long idEspectaculoDeFuncion) {
        this.idUsuario = idUsuario;
        this.idFuncionObtenida = idFuncionObtenida;
        this.funcionesCanjeadas = funcionesCanjeadas;
        this.idEspectaculoDeFuncion = idEspectaculoDeFuncion;
    }

    public CanjeTresPorUnoDTO(Long id, Long idUsuario, Long idFuncionObtenida, String funcionesCanjeadas, Date fechaRegistro) {
        this.id = id;
        this.idUsuario = idUsuario;
        this.idFuncionObtenida = idFuncionObtenida;
        this.funcionesCanjeadas = funcionesCanjeadas;
        this.fechaRegistro = fechaRegistro;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getFuncionesCanjeadas() {
        return funcionesCanjeadas;
    }

    public void setFunciones(String funciones) {
        this.funcionesCanjeadas = funcionesCanjeadas;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public Long getIdFuncionObtenida() {
        return idFuncionObtenida;
    }

    public void setIdFuncionObtenida(Long idFuncionObtenida) {
        this.idFuncionObtenida = idFuncionObtenida;
    }

    public Long getIdEspectaculoDeFuncion() {
        return idEspectaculoDeFuncion;
    }

    public void setIdEspectaculoDeFuncion(Long idEspectaculoDeFuncion) {
        this.idEspectaculoDeFuncion = idEspectaculoDeFuncion;
    }
}
