package projetos.baseSpring.dto.Medico;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record MedicoRequest(
        @NotBlank String nome,
        @NotBlank @Email String email,
        @NotBlank String crm,
        String especialidade,
        String telefone
) {}
