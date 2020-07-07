package mx.com.rlr.sga.servicio;

import java.util.List;
import mx.com.rlr.sga.domain.Persona;

public interface PersonaServiceRemote {
    public List<Persona> listarPersonas();
    
    public Persona encontrarPersonaPorId(Persona persona);
    
    public Persona encontrarPersonaPorEmail(Persona persona);
    
    public void registrarPersona(Persona persona);
    
    public void modificarPersona(Persona persona);
    
    public void eliminarPersona(Persona persona);
}
