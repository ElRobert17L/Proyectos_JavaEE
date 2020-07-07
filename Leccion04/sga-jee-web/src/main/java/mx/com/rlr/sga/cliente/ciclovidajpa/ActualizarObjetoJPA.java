package mx.com.rlr.sga.cliente.ciclovidajpa;

import javax.persistence.*;
import mx.com.rlr.sga.domain.Persona;
import org.apache.logging.log4j.*;

public class ActualizarObjetoJPA {
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
        
        //Paso 3. Termina la transaccion 1
        tx.commit();

        //Objeto en estado detached(Comprobar que se haya guardado en la base)
        log.debug("Objeto recuperado: " + persona1);
        
        //Paso 4. setValue (nuevoValor)
        persona1.setApellido("Juarez");
        
        //Paso 5. Inicia transaccion 2
        EntityTransaction tx2 = em.getTransaction();
        tx2.begin();
        
        //Paso 6. Modificamos el objeto
        em.merge(persona1);
        //em.flush(); <- se puede ocupar si no queremos cerrar la transaccion y ejecuta las SQL pendientes
        
        //Paso 7. Terminamos transaccion 2
        tx2.commit();
        
        //Objeto en estado detached ya modificado(Comprobar que se haya guardado en la base)
        log.debug("Objeto recuperado: " + persona1);
        
        //Cerramos el entity manager
        em.close();
    }
}
