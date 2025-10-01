package projetos.baseSpring.Service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import projetos.baseSpring.dto.Consulta.ConsultaRequest;
import projetos.baseSpring.dto.Consulta.ConsultaResponse;
import projetos.baseSpring.model.Consulta;
import projetos.baseSpring.model.Medico;
import projetos.baseSpring.model.Paciente;
import projetos.baseSpring.repository.ConsultaRepository;
import projetos.baseSpring.repository.MedicoRepository;
import projetos.baseSpring.repository.PacienteRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ConsultaService {

    private final ConsultaRepository repo;
    private final PacienteRepository pacienteRepo;
    private final MedicoRepository medicoRepo;

    public ConsultaService(ConsultaRepository repo, PacienteRepository pacienteRepo, MedicoRepository medicoRepo) {
        this.repo = repo;
        this.pacienteRepo = pacienteRepo;
        this.medicoRepo = medicoRepo;
    }

    @Transactional
    public ConsultaResponse criar(ConsultaRequest req) {
        Paciente p = pacienteRepo.findById(req.pacienteId())
                .orElseThrow(() -> new IllegalArgumentException("Paciente não encontrado"));
        Medico m = medicoRepo.findById(req.medicoId())
                .orElseThrow(() -> new IllegalArgumentException("Médico não encontrado"));

        // Regra simples de conflito: mesmo médico, mesma data/hora não pode
        if (!repo.findByMedicoIdAndDataHora(m.getId(), req.dataHora()).isEmpty()) {
            throw new IllegalArgumentException("Conflito de agenda: médico já possui consulta nessa data/hora");
        }

        Consulta c = new Consulta();
        c.setPaciente(p);
        c.setMedico(m);
        c.setDataHora(req.dataHora());
        c.setStatus(Consulta.Status.AGENDADA);
        c.setObservacoes(req.observacoes());

        c = repo.save(c);
        return toResponse(c);
    }

    @Transactional(readOnly = true)
    public List<ConsultaResponse> listar() {
        return repo.findAll().stream().map(this::toResponse).toList();
    }

    @Transactional(readOnly = true)
    public ConsultaResponse buscar(Long id) {
        var c = repo.findById(id).orElseThrow(() -> new IllegalArgumentException("Consulta não encontrada"));
        return toResponse(c);
    }

    @Transactional
    public ConsultaResponse atualizarDataHora(Long id, LocalDateTime novaDataHora) {
        var c = repo.findById(id).orElseThrow(() -> new IllegalArgumentException("Consulta não encontrada"));

        // conflito com o mesmo médico
        if (!repo.findByMedicoIdAndDataHora(c.getMedico().getId(), novaDataHora).isEmpty()) {
            throw new IllegalArgumentException("Conflito de agenda: médico já possui consulta nessa data/hora");
        }
        c.setDataHora(novaDataHora);
        return toResponse(c);
    }

    @Transactional
    public ConsultaResponse alterarStatus(Long id, String novoStatus) {
        var c = repo.findById(id).orElseThrow(() -> new IllegalArgumentException("Consulta não encontrada"));
        try {
            c.setStatus(Consulta.Status.valueOf(novoStatus.toUpperCase()));
        } catch (IllegalArgumentException ex) {
            throw new IllegalArgumentException("Status inválido. Use: AGENDADA, CANCELADA, CONCLUIDA");
        }
        return toResponse(c);
    }

    @Transactional
    public void deletar(Long id) {
        if (!repo.existsById(id)) throw new IllegalArgumentException("Consulta não encontrada");
        repo.deleteById(id);
    }

    // Regras de bloqueio p/ exclusão de entidades relacionadas (chamaremos nas services delas):
    @Transactional(readOnly = true)
    public boolean medicoPossuiConsultas(Long medicoId) {
        return repo.existsByMedicoId(medicoId);
    }
    @Transactional(readOnly = true)
    public boolean pacientePossuiConsultas(Long pacienteId) {
        return repo.existsByPacienteId(pacienteId);
    }

    private ConsultaResponse toResponse(Consulta c) {
        return new ConsultaResponse(
                c.getId(),
                c.getPaciente().getId(),
                c.getPaciente().getNome(),
                c.getMedico().getId(),
                c.getMedico().getNome(),
                c.getDataHora(),
                c.getStatus().name(),
                c.getObservacoes()
        );
    }
}
