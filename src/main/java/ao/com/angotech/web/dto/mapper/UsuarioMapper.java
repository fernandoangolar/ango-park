package ao.com.angotech.web.dto.mapper;

import ao.com.angotech.entity.Usuario;
import ao.com.angotech.web.dto.UsuarioCreateDto;
import ao.com.angotech.web.dto.UsuarioResponseDto;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;

import java.util.List;
import java.util.stream.Collectors;

public class UsuarioMapper {

    public static Usuario toUsuario(UsuarioCreateDto createDto) {
        return new ModelMapper().map(createDto, Usuario.class);
    }

    public static UsuarioResponseDto toDto(Usuario usuario) {
        ModelMapper mapper = new ModelMapper();

        // Converter para lidar com o role
        mapper.typeMap(Usuario.class, UsuarioResponseDto.class).setConverter(context -> {
            Usuario src = context.getSource();
            String role = src.getRole().name().substring("ROLE_".length());
            // Cria o DTO usando o construtor do record
            return new UsuarioResponseDto(
                    src.getId(),
                    src.getUsername(),
                    role
            );
        });

        return mapper.map(usuario, UsuarioResponseDto.class);
    }

    public static List<UsuarioResponseDto> toListDto(List<Usuario> usuarios) {
        return usuarios.stream().map(UsuarioMapper::toDto).collect(Collectors.toList());
    }
}
