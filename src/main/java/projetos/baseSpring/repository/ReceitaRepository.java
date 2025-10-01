package projetos.baseSpring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import projetos.baseSpring.model.Receita;

import java.util.List;

public interface ReceitaRepository extends JpaRepository<Receita, Long> {
    List<Receita> findByConsultaId(Long consultaId);
}
