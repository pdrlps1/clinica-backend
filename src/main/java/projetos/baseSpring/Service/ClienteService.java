package projetos.baseSpring.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import projetos.baseSpring.model.Cliente;
import projetos.baseSpring.repository.ClienteRepository;

@Service
public class ClienteService {
	private final ClienteRepository repository;
	
	public ClienteService(ClienteRepository repository) {
		this.repository = repository;
	}
	
	public List<Cliente> listarTodos() {
		return repository.findAll();
	}
	
	public Cliente buscarPorId(Long id) {
		return repository.findById(id).orElse(null);
	}
	
	public Cliente criar(Cliente cliente) {
		return repository.save(cliente);
	}
	
	public Cliente atualizar(Long id, Cliente clienteAtualizado) {
		return repository.findById(id).map(cliente -> {
			cliente.setNome(clienteAtualizado.getNome());
			cliente.setEmail(clienteAtualizado.getEmail());
			cliente.setTelefone(clienteAtualizado.getTelefone());
			return repository.save(cliente);
		}).orElse(null);
	}
	
	public void deletar(Long id) {
		repository.deleteById(id);
	}
	
	public List<Cliente> pesquisarPorNome(String nome) {
		return repository.getByNome(nome);
	}
}
