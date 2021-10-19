package edu.innova.webapp.helpers;

import static edu.innova.webapp.helpers.HelperImagenes.CarpetaDestinoImagenes.USUARIOS;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.nio.file.Paths;
import javax.servlet.http.Part;

public class HelperImagenes {

    private HelperProperties helperProperties = new HelperProperties().getInstance();

    private static HelperImagenes instance;
    
     private HelperImagenes() {
    }

    public static HelperImagenes getInstance() {
        if (instance == null) {
            instance = new HelperImagenes();
        }
        return instance;
    }

    public enum CarpetaDestinoImagenes {
        USUARIOS, ESPECTACULOS, PAQUETES
    };

    public boolean guardarImagen(Part filePart, CarpetaDestinoImagenes destino, String nombreImagen) {
        String imageName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
        String imageSavePath = helperProperties.getRUTA_PROYECTO() + getCarpetaDestino(destino) + File.separator + nombreImagen;

        FileOutputStream outputStream = null;
        InputStream fileContent = null;
        try {
            outputStream = new FileOutputStream(new File(imageSavePath));

            fileContent = filePart.getInputStream();

            int readBytes = 0;
            byte[] readArray = new byte[1024];

            while ((readBytes = fileContent.read(readArray)) != -1) {
                outputStream.write(readArray, 0, readBytes);
            }
            if (outputStream != null) {
                outputStream.close();

            }
            if (fileContent != null) {
                fileContent.close();
            }
        } catch (Exception ex) {
            System.out.println("Error Writing File: " + ex);
            return false;
        } finally {

        }
        return true;
    }

    public String getCarpetaDestino(CarpetaDestinoImagenes carpetaDestinoImagenes) {
        switch (carpetaDestinoImagenes) {
            case USUARIOS:
                return helperProperties.getRUTA_USUARIO();
            case ESPECTACULOS:
                return helperProperties.getRUTA_ESPECTACULOS();
            case PAQUETES:
                return helperProperties.getRUTA_PAQUETES();
            default:
                return null;
        }
    }
    
    public String crearNombreArchivo(Part part, String nickname) {
        if (part != null) {
            String imageName = Paths.get(part.getSubmittedFileName()).getFileName().toString();
            String[] split = imageName.split("\\.");

            if (split.length > 1) {
                String extencion = split[split.length - 1];
                return nickname.replaceAll("\\s+", "").concat(".").concat(extencion);
            }
        }
        return null;
    }

}
