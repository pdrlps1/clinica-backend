package projetos.baseSpring.Controller;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import projetos.baseSpring.Service.PacienteService;
import projetos.baseSpring.dto.Paciente.PacienteRequest;
import projetos.baseSpring.dto.Paciente.PacienteResponse;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/pacientes")
public class PacienteController {

    private final PacienteService service;

    public PacienteController(PacienteService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<PacienteResponse> criar(@Valid @RequestBody PacienteRequest req) {
        var resp = service.criar(req);
        return ResponseEntity.created(URI.create("/api/pacientes/" + resp.id())).body(resp);
    }

    @GetMapping
    public List<PacienteResponse> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public PacienteResponse buscar(@PathVariable Long id) {
        return service.buscar(id);
    }

    @PutMapping("/{id}")
    public PacienteResponse atualizar(@PathVariable Long id, @Valid @RequestBody PacienteRequest req) {
        return service.atualizar(id, req);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
