package projetos.baseSpring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import projetos.baseSpring.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
	List<Cliente> getByNome(String nome);
}
