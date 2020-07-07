package mx.com.rlr.sga.cliente.ciclovidajpa;

import javax.persistence.*;
import mx.com.rlr.sga.domain.Persona;
import org.apache.logging.log4j.*;

public class ActualizarObjetoSesionLarga {
    static Logger log = LogManager.getRootLogger();
    
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("SgaPU");
        EntityManager em = emf.createEntityManager();
        
        //Inicia la transaccion
        
        //Paso 1. Iniciar una transaccion
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        //Paso 2. Ejecuta SQL de tipo select
        //El id proporcionado debe existir en la base de datos
        Persona persona1 = em.find(Persona.class, 1);

        //Objeto en estado detached(Comprobar que se haya guardado en la base)
        log.debug("Objeto recuperado: " + persona1);
        
        //Paso 3. setValue (nuevoValor)
        persona1.setEmail("jjuarez@mail.com");
        
        persona1.setEmail("j_juarez@mail.com");
        
        //Paso 4. Termina la transaccion 1
        tx.commit();
        
        //Objeto en estado detached ya modificado(Comprobar que se haya guardado en la base)
        log.debug("Objeto modificado: " + persona1);
        
        //Cerramos el entity manager
        em.close();
    }
}
