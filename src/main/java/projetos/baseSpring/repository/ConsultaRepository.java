package projetos.baseSpring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import projetos.baseSpring.model.Consulta;

import java.time.LocalDateTime;
import java.util.List;

public interface ConsultaRepository extends JpaRepository<Consulta, Long> {
    List<Consulta> findByMedicoIdAndDataHora(Long medicoId, LocalDateTime dataHora);
    boolean existsByPacienteId(Long pacienteId);
    boolean existsByMedicoId(Long medicoId);
    List<Consulta> findByMedicoId(Long medicoId);
    List<Consulta> findByPacienteId(Long pacienteId);
}
