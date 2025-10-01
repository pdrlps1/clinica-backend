package projetos.baseSpring.dto.Consulta;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

public record ConsultaRequest(
        @NotNull Long pacienteId,
        @NotNull Long medicoId,
        @NotNull @Future LocalDateTime dataHora,
        String observacoes
) {}
