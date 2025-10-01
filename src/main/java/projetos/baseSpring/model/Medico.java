package projetos.baseSpring.model;

import jakarta.persistence.*;

@Entity
@Table(name = "medico", uniqueConstraints = {
        @UniqueConstraint(name = "uk_medico_email", columnNames = "email"),
        @UniqueConstraint(name = "uk_medico_crm", columnNames = "crm")
})
public class Medico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "medico")
    private java.util.List<Consulta> consultas = new java.util.ArrayList<>();
    public java.util.List<Consulta> getConsultas() { return consultas; }
    public void setConsultas(java.util.List<Consulta> consultas) { this.consultas = consultas; }

    @Column(nullable = false) private String nome;
    @Column(nullable = false) private String email;
    @Column(nullable = false) private String crm;          // ex.: "123456-SP"
    private String especialidade;                          // simplificado como String
    private String telefone;

    // getters e setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getCrm() { return crm; }
    public void setCrm(String crm) { this.crm = crm; }
    public String getEspecialidade() { return especialidade; }
    public void setEspecialidade(String especialidade) { this.especialidade = especialidade; }
    public String getTelefone() { return telefone; }
    public void setTelefone(String telefone) { this.telefone = telefone; }
}
