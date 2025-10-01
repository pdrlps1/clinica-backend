package projetos.baseSpring.Service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import projetos.baseSpring.dto.Medico.MedicoRequest;
import projetos.baseSpring.dto.Medico.MedicoResponse;
import projetos.baseSpring.model.Medico;
import projetos.baseSpring.repository.MedicoRepository;

import java.util.List;

@Service
public class MedicoService {

    private final MedicoRepository repo;

    public MedicoService(MedicoRepository repo) { this.repo = repo; }

    @Transactional
    public MedicoResponse criar(MedicoRequest req) {
        if (repo.existsByEmail(req.email())) throw new IllegalArgumentException("Email já cadastrado");
        if (repo.existsByCrm(req.crm()))     throw new IllegalArgumentException("CRM já cadastrado");
        Medico m = new Medico();
        m.setNome(req.nome());
        m.setEmail(req.email());
        m.setCrm(req.crm());
        m.setEspecialidade(req.especialidade());
        m.setTelefone(req.telefone());
        m = repo.save(m);
        return toResponse(m);
    }

    @Transactional(readOnly = true)
    public List<MedicoResponse> listar() {
        return repo.findAll().stream().map(this::toResponse).toList();
    }

    @Transactional(readOnly = true)
    public MedicoResponse buscar(Long id) {
        var m = repo.findById(id).orElseThrow(() -> new IllegalArgumentException("Médico não encontrado"));
        return toResponse(m);
    }

    @Transactional
    public MedicoResponse atualizar(Long id, MedicoRequest req) {
        var m = repo.findById(id).orElseThrow(() -> new IllegalArgumentException("Médico não encontrado"));
        if (!m.getEmail().equals(req.email()) && repo.existsByEmail(req.email()))
            throw new IllegalArgumentException("Email já cadastrado");
        if (!m.getCrm().equals(req.crm()) && repo.existsByCrm(req.crm()))
            throw new IllegalArgumentException("CRM já cadastrado");

        m.setNome(req.nome());
        m.setEmail(req.email());
        m.setCrm(req.crm());
        m.setEspecialidade(req.especialidade());
        m.setTelefone(req.telefone());
        return toResponse(m);
    }

    @Transactional
    public void deletar(Long id) {
        // regra de negócio para impedir exclusão se tiver consultas virá depois na ConsultaService
        if (!repo.existsById(id)) throw new IllegalArgumentException("Médico não encontrado");
        repo.deleteById(id);
    }

    private MedicoResponse toResponse(Medico m) {
        return new MedicoResponse(
                m.getId(), m.getNome(), m.getEmail(), m.getCrm(), m.getEspecialidade(), m.getTelefone()
        );
    }
}
