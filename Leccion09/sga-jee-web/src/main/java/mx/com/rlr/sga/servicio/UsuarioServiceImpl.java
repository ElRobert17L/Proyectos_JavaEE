package mx.com.rlr.sga.servicio;

import java.util.List;
import javax.inject.Inject;
import mx.com.rlr.sga.datos.UsuarioDao;
import mx.com.rlr.sga.domain.Usuario;

public class UsuarioServiceImpl implements UsuarioServiceRemote, UsuarioService{

    @Inject
    private UsuarioDao usuarioDao;
    
    @Override
    public List<Usuario> listarUsuario() {
        return usuarioDao.findAllUsuarios();
    }

    @Override
    public Usuario encontrarUsuarioPorId(Usuario usuario) {
        return usuarioDao.findUsuarioById(usuario);
    }

    @Override
    public Usuario encontrarUsuarioByIdPersona(Usuario usuario) {
        return usuarioDao.findUsuarioByIdPersona(usuario);
    }

    @Override
    public void registrarUsuario(Usuario usuario) {
        usuarioDao.insertUsuario(usuario);
    }

    @Override
    public void modificarUsuario(Usuario usuario) {
        usuarioDao.updateUsuario(usuario);
    }

    @Override
    public void eliminarUsuario(Usuario usuario) {
        usuarioDao.deleteUsuario(usuario);
    }
    
}
