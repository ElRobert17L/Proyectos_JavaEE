package test;

import javax.persistence.*;
import mx.com.rlr.domain.Persona;
import org.apache.logging.log4j.*;


public class ClienteEntidadPersona {
    static Logger log = LogManager.getRootLogger();
    
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("PersonaPU");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        
        //Inicia la Transaccion
        tx.begin();
        //NO se debe especificar el ID de la base de datos
        Persona persona1 = new Persona("Maria", "Gutierrez", "mgutierrez@mail", "5556211265");
        log.debug("Objeto a persistir: " + persona1);
        //Persistimos el objeto
        em.persist(persona1);
        //terminamos la transaccion
        tx.commit();
        log.debug("Objeto persistido" + persona1);
        em.close();
    }
}
