package projetos.baseSpring.Controller;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import projetos.baseSpring.Service.MedicoService;
import projetos.baseSpring.dto.Medico.MedicoRequest;
import projetos.baseSpring.dto.Medico.MedicoResponse;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/medicos")
public class MedicoController {

    private final MedicoService service;
    public MedicoController(MedicoService service) { this.service = service; }

    @PostMapping
    public ResponseEntity<MedicoResponse> criar(@Valid @RequestBody MedicoRequest req) {
        var resp = service.criar(req);
        return ResponseEntity.created(URI.create("/api/medicos/" + resp.id())).body(resp);
    }

    @GetMapping public List<MedicoResponse> listar() { return service.listar(); }

    @GetMapping("/{id}") public MedicoResponse buscar(@PathVariable Long id) { return service.buscar(id); }

    @PutMapping("/{id}") public MedicoResponse atualizar(@PathVariable Long id, @Valid @RequestBody MedicoRequest req) {
        return service.atualizar(id, req);
    }

    @DeleteMapping("/{id}") public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
