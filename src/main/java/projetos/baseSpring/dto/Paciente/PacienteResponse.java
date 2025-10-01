package projetos.baseSpring.dto.Paciente;

import java.time.LocalDate;

public record PacienteResponse(
        Long id,
        String nome,
        String email,
        String telefone,
        LocalDate dataNascimento,
        String endereco
) {}
