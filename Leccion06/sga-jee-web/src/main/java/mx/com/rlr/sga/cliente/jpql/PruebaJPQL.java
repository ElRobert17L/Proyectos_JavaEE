package mx.com.rlr.sga.cliente.jpql;

import java.util.Iterator;
import java.util.List;
import javax.persistence.*;
import mx.com.rlr.sga.domain.*;
import org.apache.logging.log4j.*;

public class PruebaJPQL {

    static Logger log = LogManager.getRootLogger();

    public static void main(String[] args) {
        String jpql = null;
        Query q = null;
        List<Persona> personas = null;
        Persona persona = null;
        Iterator iter = null;
        Object[] tupla = null;
        List<String> nombres = null;
        List<Usuario> usuarios = null;

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("SgaPU");
        EntityManager em = emf.createEntityManager();

        //Paso 1. Consulta de todos los objetos de tipo personas
        log.debug("\n1. Consulta de todas las Personas");
        jpql = "select p from Persona p";
        personas = em.createQuery(jpql).getResultList();
        // PRIMER CONSULTA : TODAS LAS PERSONAS
        //mostrarPersonas(personas);

        //SEGUNDA CONSULTA : PERSONAS CON ID = 1
        log.debug("\n2. Consulta de la Persona con id = 1");
        jpql = "select p from Persona p where p.idPersona = 1";
        persona = (Persona) em.createQuery(jpql).getSingleResult();
        //log.debug(persona);

        //TERCERA CONSULTA: PERSONAS CON EL MISMO NOMBRE
        jpql = "select p from Persona p where p.nombre = 'Karla'";
        personas = em.createQuery(jpql).getResultList();
        //mostrarPersonas(personas);

        //CUARTA CONSULTA: CONSULTA DE DATOS INDIVIDUALES, SE CREA UN ARREGLO(TUPLA) DE TIPO OBJECT DE 3 COLUMNAS
        log.debug("\n4. Consulta de datos individuales, se crea un arreglo(tupla) de tipo object de 3 columnas");
        jpql = "select p.nombre as Nombre, p.apellido as Apellido, p.email as Email from Persona p";
        iter = em.createQuery(jpql).getResultList().iterator();
        while (iter.hasNext()) {
            tupla = (Object[]) iter.next();
            String nombre = (String) tupla[0];
            String apellido = (String) tupla[1];
            String email = (String) tupla[2];
            //log.debug("nombre: " + nombre + ", apellido: " + apellido + ", email:" + email);
        }

        //QUINTA CONSULTA: OBTIENE EL OBJETO PERSONA Y EL ID, SE CREA UN ARREGLO DE TIPO OBJECT CON 2 COLUMNAS
        log.debug("\n5. Obtiene el objeto persona y el id, se crea un arreglo de tipo object con 2 columnas");
        jpql = "select p, p.idPersona from Persona p";
        iter = em.createQuery(jpql).getResultList().iterator();
        while (iter.hasNext()) {
            tupla = (Object[]) iter.next();
            persona = (Persona) tupla[0];
            int idPersona = (int) tupla[1];
            //log.debug("Objeto persona: " + persona);
            //log.debug("id persona: " + idPersona);
        }

        //SEXTA CONSULTA: CONSULTA DE TODAS LAS PERSONAS
        log.debug("\n6. Consulta de todas las personas");
        jpql = "select new mx.com.rlr.sga.domain.Persona( p.idPersona ) from Persona p";
        personas = em.createQuery(jpql).getResultList();
        //mostrarPersonas(personas);

        //SEPTIMA CONSULTA: REGRESA EL VALOR MINIMO Y MAXIMO DEL IdPersona (SCALER RESULT)
        log.debug("\n7. Regresa el valor minimo y maximo del ipPersona (scaler result)");
        jpql = "select min(p.idPersona) as MinId, max(p.idPersona) as MaxId, count(p.idPersona) as Contador from Persona p";
        iter = em.createQuery(jpql).getResultList().iterator();
        while (iter.hasNext()) {
            tupla = (Object[]) iter.next();
            Integer idMin = (Integer) tupla[0];
            Integer idMax = (Integer) tupla[1];
            Long count = (Long) tupla[2];
            //log.debug("idMin: " + idMin + ", idMax: " + idMax + ", count: " + count);
        }

        //OCTAVA CONSULTA: CUENTA LOS NOMBRES DE LAS PERSONAS QUE SON DISTINTOS
        log.debug("\n8. Cuenta los nombres de las personas que son distintos");
        jpql = "select count(distinct p.nombre) from Persona p";
        Long contador = (Long) em.createQuery(jpql).getSingleResult();
        //log.debug("no. de personas con nombre distinto: " + contador);

        //NOVENA CONSULTA: CONCATENA Y CONVIERTE A MAYUSCULAS EL NOMBRE Y APELLIDO
        log.debug("\n9. Concatena y convierte a mayusculas el nombre y apellido");
        jpql = "select CONCAT(p.nombre, ' ', p.apellido) as Nombre from Persona p";
        nombres = em.createQuery(jpql).getResultList();
        for(String nombreCompleto: nombres){
        //log.debug(nombreCompleto);
        }
        
        //DECIMA CONSULTA: OBTIENE EL OBJETO CON ID IGUAL AL PARAMETRO PROPORCIONADO
        log.debug("\n10. Obtiene el objeto con ID igual al parametro proporcionado");
        int idPersona = 1;
        jpql = "select p from Persona p where p.idPersona = :id";
        q = em.createQuery(jpql);
        q.setParameter("id", idPersona);
        persona = (Persona) q.getSingleResult();
        //log.debug(persona);
        
        /*ONCEAVA CONSULTA: OBTIENE LAS PERSONAS QUE CONTENGAN UNA LETRA a EN EL NOMBRE
                            SIN IMPORTAR SI ES MAYUSCULA O MINUSCULA */
        log.debug("\n11. Obtiene las personas que contengan una letra -a- en el nombre sin importar"
                + "si es mayuscula o minuscula");
        jpql = "select p from Persona p where upper(p.nombre) like upper(:parametro)";
        String parametroString = "%a%";//es el caracter que utilizamos para el like (%_%)
        q = em.createQuery(jpql);
        q.setParameter("parametro", parametroString);
        personas = q.getResultList();
        //mostrarPersonas(personas);
        
        //DOCEAVA CONSULTA: USO DE BETWEEN
        log.debug("\n12. Uso de between");
        jpql = "select p from Persona p where p.idPersona between 1 and 10";
        personas = em.createQuery(jpql).getResultList();
        //mostrarPersonas(personas);
        
        //TRECEAVA CONSULTA: USO DE ORDENAMIENTO
        log.debug("\n13. Uso de ordenamiento");
        jpql = "select p from Persona p where p.idPersona > 1 order by p.nombre desc, p.apellido desc";
        personas = em.createQuery(jpql).getResultList();
        //mostrarPersonas(personas);
        
        //CATORCEAVA CONSULTA: USO DE SUBQUERY
        log.debug("\n14. Uso de subquery");
        jpql = "select p from Persona p where p.idPersona in (select min(p1.idPersona) from Persona p1)";
        personas = em.createQuery(jpql).getResultList();
        //mostrarPersonas(personas);
        
        //QUINCEAVA CONSULTA: USO DE JOIN CON LAZY LOADING
        log.debug("\n15. Uso de join con lazy loading");
        jpql = "select u from Usuario u join u.persona p";
        usuarios = em.createQuery(jpql).getResultList();
        //mostrarUsuarios(usuarios);
        
        //DIECISEISAVA CONSULTA: USO DE LEFT JOIN CON EAGER LOADING
        log.debug("\n16. Uso de left join con eager loading");
        jpql = "select u from Usuario u left join fetch u.persona"; //fetch: eager loading
        usuarios = em.createQuery(jpql).getResultList();
        mostrarUsuarios(usuarios);
    }

    private static void mostrarPersonas(List<Persona> personas) {
        for (Persona p : personas) {
            log.debug(p);
        }
    }
    
    private static void mostrarUsuarios(List<Usuario> usuarios) {
        for (Usuario u : usuarios) {
            log.debug(u);
        }
    }
}
