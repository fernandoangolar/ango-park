package ao.com.angotech.web.controller;

import ao.com.angotech.entity.Usuario;
import ao.com.angotech.service.UsuarioService;
import ao.com.angotech.web.dto.UsuarioCreateDto;
import ao.com.angotech.web.dto.UsuarioResponseDto;
import ao.com.angotech.web.dto.UsuarioSenhaDto;
import ao.com.angotech.web.dto.mapper.UsuarioMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/usuarios")
public class UsuarioController {

    public UsuarioService usuarioService;

    public UsuarioController (UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping
    public ResponseEntity<UsuarioResponseDto> create(@RequestBody UsuarioCreateDto createDto) {
        Usuario user = usuarioService.salvar(UsuarioMapper.toUsuario(createDto));

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(UsuarioMapper.toDto(user));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponseDto> getById(@PathVariable Long id) {
        Usuario user = usuarioService.buscarPorId(id);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(UsuarioMapper.toDto(user));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> updatePassword(@PathVariable Long id, @RequestBody UsuarioSenhaDto dto) {
        Usuario user = usuarioService.editarSenha(id, dto.senhaAtual(), dto.novaSenha(), dto.confirmaSenha());

        return ResponseEntity
                .noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<UsuarioResponseDto>> getAll() {
        List<Usuario> user = usuarioService.buscarTodos();

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(UsuarioMapper.toListDto(user));
    }

}
