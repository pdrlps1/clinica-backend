package projetos.baseSpring.Controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import projetos.baseSpring.Service.ClienteService;
import projetos.baseSpring.model.Cliente;

@RestController
@RequestMapping("/Cliente")
public class ClienteController {
	private final ClienteService service;
	
	public ClienteController(ClienteService service) {
		this.service = service;
	}

	@GetMapping
	public List<Cliente> listarTodos() {
		return service.listarTodos();
	}
	
	@GetMapping("/{id}")
	public Cliente buscarPorId(@PathVariable Long id) {
		return service.buscarPorId(id);
	}
	
	@GetMapping("/pesquisar")
	public List<Cliente> buscarPorNome(@RequestParam String nome) {
		return service.pesquisarPorNome(nome);
	}
	
	@PostMapping
	public Cliente criar(@RequestBody Cliente cliente) {
		return service.criar(cliente);
	}	
	
	@PutMapping("/{id}")
	public Cliente atualizar(@PathVariable Long id, @RequestBody Cliente cliente) {
	    return service.atualizar(id, cliente);
	}

	@DeleteMapping("/{id}")
	public void deletar(@PathVariable Long id) {
	    service.deletar(id);
	}
}
