package projetos.baseSpring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import projetos.baseSpring.model.Paciente;

import java.util.Optional;

public interface PacienteRepository extends JpaRepository<Paciente, Long> {
    boolean existsByEmail(String email);
    Optional<Paciente> findByEmail(String email);
}
