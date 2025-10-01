package projetos.baseSpring.Controller;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import projetos.baseSpring.Service.ReceitaService;
import projetos.baseSpring.dto.Receita.ReceitaRequest;
import projetos.baseSpring.dto.Receita.ReceitaResponse;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/receitas")
public class ReceitaController {

    private final ReceitaService service;
    public ReceitaController(ReceitaService service) { this.service = service; }

    @PostMapping
    public ResponseEntity<ReceitaResponse> criar(@Valid @RequestBody ReceitaRequest req) {
        var resp = service.criar(req);
        return ResponseEntity.created(URI.create("/api/receitas/" + resp.id())).body(resp);
    }

    @GetMapping
    public List<ReceitaResponse> listar() {
        return service.listar();
    }

    @GetMapping("/por-consulta/{consultaId}")
    public List<ReceitaResponse> listarPorConsulta(@PathVariable Long consultaId) {
        return service.listarPorConsulta(consultaId);
    }

    @GetMapping("/{id}")
    public ReceitaResponse buscar(@PathVariable Long id) {
        return service.buscar(id);
    }

    @PutMapping("/{id}")
    public ReceitaResponse atualizar(@PathVariable Long id, @Valid @RequestBody ReceitaRequest req) {
        return service.atualizar(id, req);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
