package mx.com.rlr.cliente;

import java.util.List;
import javax.naming.*;
import mx.com.rlr.sga.domain.Persona;
import mx.com.rlr.sga.servicio.PersonaServiceRemote;

public class Cliente {
    public static void main(String[] args) {
        System.out.println("Iniciando llamada al EJB desde el Cliente\n");
        try {
            Context jndi = new InitialContext();
            PersonaServiceRemote personaService = (PersonaServiceRemote) jndi.lookup("java:global/sga-jee/PersonaServiceImpl!mx.com.rlr.sga.servicio.PersonaServiceRemote");
            
            List<Persona> personas = personaService.listarPersona();
            
            for(Persona persona : personas) {
                System.out.println(persona);
            }
            
            System.out.println("\nFin llamada el EJB desde el Cliente");
                    
        } catch (NamingException ex) {
            ex.printStackTrace(System.out);
        }
    }
}
