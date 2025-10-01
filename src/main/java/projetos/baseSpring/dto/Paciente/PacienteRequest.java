package projetos.baseSpring.dto.Paciente;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;

public record PacienteRequest(
        @NotBlank String nome,
        @NotBlank @Email String email,
        String telefone,
        LocalDate dataNascimento,
        String endereco
) {}
