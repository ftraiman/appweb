package edu.innova.webapp.helpers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;


public class HelperFechas {
    
    public static Date stringToDate(String fecha, String patron) {
        
        SimpleDateFormat format = new SimpleDateFormat(patron);
        try {
            return format.parse(fecha);
        } catch (ParseException ex) {
            Logger.getLogger(HelperFechas.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public static String dateToString(Date fecha, String patron) {
        SimpleDateFormat format = new SimpleDateFormat(patron);
        return format.format(fecha);
        
    }
        
}
