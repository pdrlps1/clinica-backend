package projetos.baseSpring.Controller;

import jakarta.validation.Valid;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import projetos.baseSpring.Service.ConsultaService;
import projetos.baseSpring.dto.Consulta.ConsultaRequest;
import projetos.baseSpring.dto.Consulta.ConsultaResponse;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/consultas")
public class ConsultaController {

    private final ConsultaService service;
    public ConsultaController(ConsultaService service) { this.service = service; }

    @PostMapping
    public ResponseEntity<ConsultaResponse> criar(@Valid @RequestBody ConsultaRequest req) {
        var resp = service.criar(req);
        return ResponseEntity.created(URI.create("/api/consultas/" + resp.id())).body(resp);
    }

    @GetMapping public List<ConsultaResponse> listar() { return service.listar(); }

    @GetMapping("/{id}") public ConsultaResponse buscar(@PathVariable Long id) { return service.buscar(id); }

    @PatchMapping("/{id}/data-hora")
    public ConsultaResponse atualizarDataHora(
            @PathVariable Long id,
            @RequestParam("valor")
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime novaDataHora
    ) {
        return service.atualizarDataHora(id, novaDataHora);
    }

    @PatchMapping("/{id}/status")
    public ConsultaResponse alterarStatus(@PathVariable Long id, @RequestParam("valor") String status) {
        return service.alterarStatus(id, status);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
