package projetos.baseSpring.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "consulta", indexes = {
        @Index(name = "idx_consulta_medico_data", columnList = "medico_id, data_hora")
})
public class Consulta {

    public enum Status { AGENDADA, CANCELADA, CONCLUIDA }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "data_hora", nullable = false)
    private LocalDateTime dataHora;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status = Status.AGENDADA; // corrigiremos para AGENDADA já já

    @Column(columnDefinition = "TEXT")
    private String observacoes;

    @ManyToOne(optional = false)
    @JoinColumn(name = "paciente_id", nullable = false,
            foreignKey = @ForeignKey(name = "fk_consulta_paciente"))
    private Paciente paciente;

    @ManyToOne(optional = false)
    @JoinColumn(name = "medico_id", nullable = false,
            foreignKey = @ForeignKey(name = "fk_consulta_medico"))
    private Medico medico;

    @OneToMany(mappedBy = "consulta")
    private java.util.List<Receita> receitas = new java.util.ArrayList<>();
    public java.util.List<Receita> getReceitas() { return receitas; }
    public void setReceitas(java.util.List<Receita> receitas) { this.receitas = receitas; }

    // getters e setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public LocalDateTime getDataHora() { return dataHora; }
    public void setDataHora(LocalDateTime dataHora) { this.dataHora = dataHora; }
    public Status getStatus() { return status; }
    public void setStatus(Status status) { this.status = status; }
    public String getObservacoes() { return observacoes; }
    public void setObservacoes(String observacoes) { this.observacoes = observacoes; }
    public Paciente getPaciente() { return paciente; }
    public void setPaciente(Paciente paciente) { this.paciente = paciente; }
    public Medico getMedico() { return medico; }
    public void setMedico(Medico medico) { this.medico = medico; }
}
