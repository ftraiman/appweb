package edu.innova.webapp.helpers;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HelperProperties {

    private static String RUTA_PROYECTO;
    private static String RUTA_USUARIO;
    private static String RUTA_ESPECTACULOS;
    private static String RUTA_PAQUETES;
    private static String RUTA_FUNCIONES;
    private HelperProperties helperProperties;

    public HelperProperties() {
    }

    public HelperProperties getInstance() {
        if (helperProperties == null) {
            Properties properties;
            try {
                properties = getProps();
                RUTA_PROYECTO = properties.getProperty("ruta.proyecto");
                RUTA_USUARIO = properties.getProperty("ruta.imagenes.usuarios");
                RUTA_ESPECTACULOS = properties.getProperty("ruta.imagenes.espectaculos");
                RUTA_PAQUETES = properties.getProperty("ruta.imagenes.paquetes");
                RUTA_FUNCIONES = properties.getProperty("ruta.imagenes.funciones");
            } catch (IOException ex) {
                Logger.getLogger(HelperProperties.class.getName()).log(Level.SEVERE, null, ex);
            }
            helperProperties = this;
        }

        return helperProperties;
    }

    public Properties getProps() throws FileNotFoundException, IOException {

        InputStream inputStream = this.getClass().getClassLoader()
                .getResourceAsStream("props.properties");
        Properties properties = new Properties();
        properties.load(inputStream);
        return properties;

    }

    public String getRUTA_PROYECTO() {
        return RUTA_PROYECTO;
    }

    public String getRUTA_USUARIO() {
        return RUTA_USUARIO;
    }

    public String getRUTA_ESPECTACULOS() {
        return RUTA_ESPECTACULOS;
    }

    public String getRUTA_PAQUETES() {
        return RUTA_PAQUETES;
    }   

    public String getRUTA_FUNCIONES() {
        return RUTA_FUNCIONES;
    }
}
