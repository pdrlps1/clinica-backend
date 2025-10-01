package projetos.baseSpring.Service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import projetos.baseSpring.dto.Paciente.PacienteRequest;
import projetos.baseSpring.dto.Paciente.PacienteResponse;
import projetos.baseSpring.model.Paciente;
import projetos.baseSpring.repository.PacienteRepository;

import java.util.List;

@Service
public class PacienteService {

    private final PacienteRepository repo;

    public PacienteService(PacienteRepository repo) {
        this.repo = repo;
    }

    @Transactional
    public PacienteResponse criar(PacienteRequest req) {
        if (repo.existsByEmail(req.email())) {
            throw new IllegalArgumentException("Email já cadastrado");
        }
        Paciente p = new Paciente();
        p.setNome(req.nome());
        p.setEmail(req.email());
        p.setTelefone(req.telefone());
        p.setDataNascimento(req.dataNascimento());
        p.setEndereco(req.endereco());
        p = repo.save(p);
        return toResponse(p);
    }

    @Transactional(readOnly = true)
    public List<PacienteResponse> listar() {
        return repo.findAll().stream().map(this::toResponse).toList();
    }

    @Transactional(readOnly = true)
    public PacienteResponse buscar(Long id) {
        Paciente p = repo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Paciente não encontrado"));
        return toResponse(p);
    }

    @Transactional
    public PacienteResponse atualizar(Long id, PacienteRequest req) {
        Paciente p = repo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Paciente não encontrado"));

        // se trocar email, valida unicidade
        if (!p.getEmail().equals(req.email()) && repo.existsByEmail(req.email())) {
            throw new IllegalArgumentException("Email já cadastrado");
        }
        p.setNome(req.nome());
        p.setEmail(req.email());
        p.setTelefone(req.telefone());
        p.setDataNascimento(req.dataNascimento());
        p.setEndereco(req.endereco());
        return toResponse(p);
    }

    @Transactional
    public void deletar(Long id) {
        // sem cascade: idealmente, quando criarmos Consulta, vamos bloquear delete se houver consultas
        if (!repo.existsById(id)) {
            throw new IllegalArgumentException("Paciente não encontrado");
        }
        repo.deleteById(id);
    }

    private PacienteResponse toResponse(Paciente p) {
        return new PacienteResponse(
                p.getId(),
                p.getNome(),
                p.getEmail(),
                p.getTelefone(),
                p.getDataNascimento(),
                p.getEndereco()
        );
    }
}
