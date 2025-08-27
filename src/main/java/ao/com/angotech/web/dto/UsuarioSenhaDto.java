package ao.com.angotech.web.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UsuarioSenhaDto(

        @NotBlank
        @Size(min = 6, max = 6)
        String senhaAtual,

        @NotBlank
        @Size(min = 6, max = 6)
        String novaSenha,

        @NotBlank
        @Size(min = 6, max = 6)
        String confirmaSenha
) {
}
