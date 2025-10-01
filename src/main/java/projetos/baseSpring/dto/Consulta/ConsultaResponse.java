package projetos.baseSpring.dto.Consulta;

import java.time.LocalDateTime;

public record ConsultaResponse(
        Long id,
        Long pacienteId,
        String pacienteNome,
        Long medicoId,
        String medicoNome,
        LocalDateTime dataHora,
        String status,
        String observacoes
) {}
