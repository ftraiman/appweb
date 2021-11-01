/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.innova.webapp.exceptions;

/**
 *
 * @author federico
 */
public class InnovaModelException extends RuntimeException {

    public InnovaModelException(String message) {
        super(message);
    }

    public InnovaModelException(String message, Throwable cause) {
        super(message, cause);
    }
     
}
