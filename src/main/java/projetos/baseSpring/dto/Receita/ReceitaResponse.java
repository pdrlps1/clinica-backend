package projetos.baseSpring.dto.Receita;

public record ReceitaResponse(
        Long id,
        Long consultaId,
        String medicamento,
        String dosagem,
        String instrucoes
) {}
