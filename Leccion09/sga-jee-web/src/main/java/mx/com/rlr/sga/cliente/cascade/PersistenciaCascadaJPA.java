package mx.com.rlr.sga.cliente.cascade;

import javax.persistence.*;
import mx.com.rlr.sga.domain.*;
import org.apache.logging.log4j.*;

public class PersistenciaCascadaJPA {

    static Logger log = LogManager.getRootLogger();

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("SgaPU");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        
        tx.begin();

        //Paso 1. Crea nuevo objeto
        //Objeto en estado transitivo
        Persona persona1 = new Persona("Hugo", "Hernandez", "hhernandez@mail.com", "5598912237");

        //Crear objeto usuario (tiene dependencia con el objeto persona)
        Usuario usuario1 = new Usuario("hhernandez", "123", persona1);

        //Paso 3. persistimos el objeto usuario unicamente
        em.persist(usuario1);

        //Paso 4. Commit transaccion
        tx.commit();

        //Objetos en estados detached
        log.debug("objeto persistido persona: " + persona1);
        log.debug("objeto persistido usuario: " + usuario1);
    }
}
