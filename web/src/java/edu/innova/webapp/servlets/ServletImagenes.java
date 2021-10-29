package edu.innova.webapp.servlets;

import edu.innova.webapp.helpers.HelperImagenes;
import edu.innova.webapp.helpers.HelperImagenes.CarpetaDestinoImagenes;
import edu.innova.webapp.helpers.HelperProperties;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "imagenes", urlPatterns = {"/imagenes"})
public class ServletImagenes extends HttpServlet {

    HelperProperties helperProperties = new HelperProperties().getInstance();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws FileNotFoundException, IOException {

        HelperImagenes helperImagenes = HelperImagenes.getInstance();

        String carpeta= "";
        switch (request.getParameter("carpeta")) {
            case "usuarios":
                carpeta = helperImagenes.getCarpetaDestino(CarpetaDestinoImagenes.USUARIOS);
                break;
            case "espectaculos":
                carpeta = helperImagenes.getCarpetaDestino(CarpetaDestinoImagenes.ESPECTACULOS);
                break;
            case "paquetes":
                carpeta = helperImagenes.getCarpetaDestino(CarpetaDestinoImagenes.PAQUETES);
                break;
        }

        String archivo = request.getParameter("archivo");
        
        FileInputStream fin = null;
        try {
            fin = new FileInputStream(helperProperties.getRUTA_PROYECTO() + File.separator + carpeta + File.separator + archivo);
        } catch (Exception e) {
            fin = new FileInputStream(helperProperties.getRUTA_PROYECTO() + File.separator + "imagenes" + File.separator + "notfound.jpg");
        }
        

        ServletOutputStream out;
        out = response.getOutputStream();
        BufferedInputStream bin = new BufferedInputStream(fin);
        BufferedOutputStream bout = new BufferedOutputStream(out);

        int ch = 0;
        while ((ch = bin.read()) != -1) {
            bout.write(ch);
        }
        bin.close();
        fin.close();
        bout.close();
    }

    public byte[] readStream(InputStream inStream) {
        ByteArrayOutputStream bops = new ByteArrayOutputStream();
        int data = -1;
        try {
            while ((data = inStream.read()) != -1) {
                bops.write(data);
            }
            return bops.toByteArray();
        } catch (Exception e) {
            return null;
        }
    }

}
