package projetos.baseSpring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import projetos.baseSpring.model.Medico;

public interface MedicoRepository extends JpaRepository<Medico, Long> {
    boolean existsByEmail(String email);
    boolean existsByCrm(String crm);
}
