package ao.com.angotech.service;

import ao.com.angotech.entity.Usuario;
import ao.com.angotech.repository.UsuarioRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UsuarioService {

    public UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Transactional
    public Usuario salvar(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    @Transactional(readOnly = true)
    public Usuario buscarPorId(Long id) {
        return usuarioRepository.findById(id)
                .orElseThrow( () -> new RuntimeException("Usuário não encontrado") );
    }

    @Transactional
    public Usuario editarSenha(Long id, Usuario usuario) {
        Usuario user = buscarPorId(id);
        user.setPassword(usuario.getPassword());

        return user;
    }

    @Transactional(readOnly = true)
    public List<Usuario> buscarTodos() {
        return usuarioRepository.findAll();
    }
}
