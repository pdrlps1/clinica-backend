package projetos.baseSpring.model;

import jakarta.persistence.*;

@Entity
@Table(name = "receita")
public class Receita {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // N:1 com Consulta
    @ManyToOne(optional = false)
    @JoinColumn(name = "consulta_id", nullable = false,
            foreignKey = @ForeignKey(name = "fk_receita_consulta"))
    private Consulta consulta;

    @Column(nullable = false)
    private String medicamento;

    private String dosagem;
    private String instrucoes;

    // getters & setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Consulta getConsulta() { return consulta; }
    public void setConsulta(Consulta consulta) { this.consulta = consulta; }
    public String getMedicamento() { return medicamento; }
    public void setMedicamento(String medicamento) { this.medicamento = medicamento; }
    public String getDosagem() { return dosagem; }
    public void setDosagem(String dosagem) { this.dosagem = dosagem; }
    public String getInstrucoes() { return instrucoes; }
    public void setInstrucoes(String instrucoes) { this.instrucoes = instrucoes; }
}
