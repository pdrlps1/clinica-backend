package projetos.baseSpring.Service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import projetos.baseSpring.dto.Receita.ReceitaRequest;
import projetos.baseSpring.dto.Receita.ReceitaResponse;
import projetos.baseSpring.model.Consulta;
import projetos.baseSpring.model.Receita;
import projetos.baseSpring.repository.ConsultaRepository;
import projetos.baseSpring.repository.ReceitaRepository;

import java.util.List;

@Service
public class ReceitaService {

    private final ReceitaRepository repo;
    private final ConsultaRepository consultaRepo;

    public ReceitaService(ReceitaRepository repo, ConsultaRepository consultaRepo) {
        this.repo = repo;
        this.consultaRepo = consultaRepo;
    }

    @Transactional
    public ReceitaResponse criar(ReceitaRequest req) {
        Consulta c = consultaRepo.findById(req.consultaId())
                .orElseThrow(() -> new IllegalArgumentException("Consulta não encontrada"));

        Receita r = new Receita();
        r.setConsulta(c);
        r.setMedicamento(req.medicamento());
        r.setDosagem(req.dosagem());
        r.setInstrucoes(req.instrucoes());
        r = repo.save(r);
        return toResponse(r);
    }

    @Transactional(readOnly = true)
    public List<ReceitaResponse> listar() {
        return repo.findAll().stream().map(this::toResponse).toList();
    }

    @Transactional(readOnly = true)
    public List<ReceitaResponse> listarPorConsulta(Long consultaId) {
        return repo.findByConsultaId(consultaId).stream().map(this::toResponse).toList();
    }

    @Transactional(readOnly = true)
    public ReceitaResponse buscar(Long id) {
        var r = repo.findById(id).orElseThrow(() -> new IllegalArgumentException("Receita não encontrada"));
        return toResponse(r);
    }

    @Transactional
    public ReceitaResponse atualizar(Long id, ReceitaRequest req) {
        var r = repo.findById(id).orElseThrow(() -> new IllegalArgumentException("Receita não encontrada"));
        if (!r.getConsulta().getId().equals(req.consultaId())) {
            // permitir trocar de consulta? normalmente não; se quiser permitir:
            var nova = consultaRepo.findById(req.consultaId())
                    .orElseThrow(() -> new IllegalArgumentException("Consulta não encontrada"));
            r.setConsulta(nova);
        }
        r.setMedicamento(req.medicamento());
        r.setDosagem(req.dosagem());
        r.setInstrucoes(req.instrucoes());
        return toResponse(r);
    }

    @Transactional
    public void deletar(Long id) {
        if (!repo.existsById(id)) throw new IllegalArgumentException("Receita não encontrada");
        repo.deleteById(id);
    }

    private ReceitaResponse toResponse(Receita r) {
        return new ReceitaResponse(
                r.getId(),
                r.getConsulta().getId(),
                r.getMedicamento(),
                r.getDosagem(),
                r.getInstrucoes()
        );
    }
}
