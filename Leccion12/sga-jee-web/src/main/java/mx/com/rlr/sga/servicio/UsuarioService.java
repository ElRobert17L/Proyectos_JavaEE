package mx.com.rlr.sga.servicio;

import java.util.List;
import javax.ejb.Local;
import mx.com.rlr.sga.domain.Usuario;

@Local
public interface UsuarioService {
    public List<Usuario> listarUsuario();
    
    public Usuario encontrarUsuarioPorId(Usuario usuario);
    
    public Usuario encontrarUsuarioByIdPersona(Usuario usuario);
    
    public void registrarUsuario(Usuario usuario);
    
    public void modificarUsuario(Usuario usuario);
    
    public void eliminarUsuario(Usuario usuario);
}
