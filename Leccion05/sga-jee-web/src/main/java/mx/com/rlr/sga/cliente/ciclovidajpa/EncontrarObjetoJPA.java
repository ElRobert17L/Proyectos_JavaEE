package mx.com.rlr.sga.cliente.ciclovidajpa;

import javax.persistence.*;
import mx.com.rlr.sga.domain.Persona;
import org.apache.logging.log4j.*;

public class EncontrarObjetoJPA {
    static Logger log = LogManager.getRootLogger();
    
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("SgaPU");
        EntityManager em = emf.createEntityManager();
        
        //Inicia la transaccion
        
        //Paso 1. Iniciar una transaccion
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        //Paso 2. Ejecuta SQL de tipo select
        Persona persona1 = em.find(Persona.class, 1);
        
        //Paso 3. Termina la transaccion
        tx.commit();
        
        //Paso 4. Objeto en estado detached(Comprobar que se haya guardado en la base)
        log.debug("Objeto recuperado: " + persona1);
        
        //Cerramos el entity manager
        em.close();
    }
}
