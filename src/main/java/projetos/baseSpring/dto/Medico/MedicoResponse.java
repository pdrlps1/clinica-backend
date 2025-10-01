package projetos.baseSpring.dto.Medico;

public record MedicoResponse(
        Long id,
        String nome,
        String email,
        String crm,
        String especialidade,
        String telefone
) {}
