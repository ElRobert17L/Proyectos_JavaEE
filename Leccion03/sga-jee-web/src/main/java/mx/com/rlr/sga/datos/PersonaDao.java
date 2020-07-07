//Capa logica de datos
package mx.com.rlr.sga.datos;

import java.util.List;
import mx.com.rlr.sga.domain.Persona;

public interface PersonaDao {
    public List<Persona> findAllPersonas();
    
    public Persona finPersonaById(Persona persona);
    
    public Persona findPersonaByEmail(Persona persona);
    
    public void insertPersona(Persona persona);
    
    public void updatePersona(Persona persona);
    
    public void deletePersona(Persona persona);
}
