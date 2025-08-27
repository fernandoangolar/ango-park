package ao.com.angotech.service;

import ao.com.angotech.entity.Usuario;
import ao.com.angotech.exception.EntityNotFoundException;
import ao.com.angotech.exception.PasswordInvalidException;
import ao.com.angotech.exception.UsernameUniqueViolationException;
import ao.com.angotech.repository.UsuarioRepository;
import ao.com.angotech.web.dto.UsuarioSenhaDto;
import org.springframework.dao.DataIntegrityViolationException;
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
        try {
            return usuarioRepository.save(usuario);
        } catch (DataIntegrityViolationException ex) {
            throw new UsernameUniqueViolationException(String.format("Username '%s' já cadastrado", usuario.getUsername()));
        }

    }

    @Transactional(readOnly = true)
    public Usuario buscarPorId(Long id) {
        return usuarioRepository.findById(id)
                .orElseThrow( () -> new EntityNotFoundException(String.format("Usuário id=%s não encontrado", id)) );
    }

    @Transactional
    public Usuario editarSenha(Long id, String senhaAtual, String novaSenha, String confirmaSenha) {

        if ( !novaSenha.equals(confirmaSenha) ) {
            throw new PasswordInvalidException("NOva Senha não confere com confirma~]ao de senha");
        }

        Usuario usuario = buscarPorId(id);
        if ( !usuario.getPassword().equals(senhaAtual) ) {
            throw new PasswordInvalidException("Sua senha não confere");
        }

        usuario.setPassword(novaSenha);
        return usuario;
    }

    @Transactional(readOnly = true)
    public List<Usuario> buscarTodos() {
        return usuarioRepository.findAll();
    }
}
