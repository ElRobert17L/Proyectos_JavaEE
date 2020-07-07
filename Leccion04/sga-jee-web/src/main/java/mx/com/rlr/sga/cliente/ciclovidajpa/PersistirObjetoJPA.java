package mx.com.rlr.sga.cliente.ciclovidajpa;

import javax.persistence.*;
import mx.com.rlr.sga.domain.Persona;
import org.apache.logging.log4j.*;

public class PersistirObjetoJPA {
    static Logger log = LogManager.getRootLogger();
    
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("SgaPU");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        
        //Inicia la transaccion
        
        //Paso 1. Crea nuevo objeto
        //Objeto en estado transitivo
        Persona persona1 = new Persona("Pedro", "Luna", "pluna@mail.com", "5547994465");
        
        
        //Paso 2. Inicia transaccion
        tx.begin();
        
        //Paso 3. Ejecuta SQL
        em.persist(persona1);
        log.debug("Objeto persistido - aun sin commit: " + persona1);
        
        //Paso 4. Commit/Rollback
        tx.commit();
        
        //Objeto en estado detached(Comprobar que se haya guardado en la base)
        log.debug("Objeto persistido - estado detached: " + persona1);
        
        //Cerramos el entity manager
        em.close();
    }
}
