package mx.com.rlr.sga.servicio;

import java.util.List;
import javax.ejb.Local;
import mx.com.rlr.sga.domain.Persona;

@Local
public interface PersonaService {
    public List<Persona> listarPersona();
    
    public Persona encontrarPersonaPorId(Persona persona);
    
    public Persona encontrarPersonaPorEmail(Persona persona);
    
    public void registrarPersona(Persona persona);
    
    public void modificarPersona(Persona persona);
    
    public void eliminarPersona(Persona persona);
}
