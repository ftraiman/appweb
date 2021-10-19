package edu.innova.webapp.logica.servicios.impl;

import edu.innova.logica.controladores.EspectaculoControlador;
import edu.innova.logica.controladores.PlataformaControlador;
import edu.innova.logica.controladores.impl.EspectaculosControladorImpl;
import edu.innova.logica.controladores.impl.PlataformaControladorImpl;
import edu.innova.webapp.dtos.CategoriaDTO;
import edu.innova.webapp.dtos.PlataformaDTO;
import java.util.List;
import java.util.stream.Collectors;

public class ServicioMenu {

    private static final EspectaculoControlador espectaculosControlador = new EspectaculosControladorImpl().getInstance();
    private static final PlataformaControlador plataformaControlador = new PlataformaControladorImpl().getInstance();

    public static List<CategoriaDTO> getCategoriasDTO() {
        return getCategoriasDelBackend();
    }

    public static List<PlataformaDTO> getPlataformas() {
        return getPlataformasDelBackend();
    }

    private static List<PlataformaDTO> getPlataformasDelBackend() {
       return plataformaControlador.getTodasLasPlataformasDTO().stream().map(ServicioMenu::plataformaDtoMapper).collect(Collectors.toList());
    }
    
    private static PlataformaDTO plataformaDtoMapper(edu.innova.logica.dtos.PlataformaDTO plataforma) {
        return new PlataformaDTO(plataforma.getId(), plataforma.getNombre(), plataforma.getDescripcion(), plataforma.getUrl());
    }

    private static List<CategoriaDTO> getCategoriasDelBackend() {
        return espectaculosControlador.getTodasLasCategoriasDTO().stream().map(ServicioMenu::categoriaDtoMapper).collect(Collectors.toList());
    }

    private static CategoriaDTO categoriaDtoMapper(edu.innova.logica.dtos.CategoriaDTO categoria) {
        return new CategoriaDTO(categoria.getId(), categoria.getNombre());
    }

}
