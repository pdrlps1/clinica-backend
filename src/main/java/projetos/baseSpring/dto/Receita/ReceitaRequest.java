package projetos.baseSpring.dto.Receita;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ReceitaRequest(
        @NotNull Long consultaId,
        @NotBlank String medicamento,
        String dosagem,
        String instrucoes
) {}
