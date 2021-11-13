package edu.innova.webapp;

import edu.innova.webapp.dtos.UsuarioDTO;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class PruebaCliente {
    
    public static void main(String[] args) {
        Client client = ClientBuilder.newClient();
        
        Object customer = new Object();
        //POST
//        Response responsePost =
//            client.target("http://localhost:8080/customer/webapi/Customer")
//            .request(MediaType.APPLICATION_XML)
//            .post(Entity.entity(customer, MediaType.APPLICATION_JSON),
//                    Response.class);
        
        UsuarioDTO responseGet =
            client.target("http://localhost:8080/ws/api/usuarios/1")
            .request(MediaType.APPLICATION_JSON)
            .get(UsuarioDTO.class);
        System.err.println(responseGet);
        
        
        
    }
    
}
