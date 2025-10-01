package projetos.baseSpring.model;

import jakarta.persistence.*;
import lombok.Getter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@Table(name = "paciente", uniqueConstraints = {
        @UniqueConstraint(name = "uk_paciente_email", columnNames = "email")
})
public class Paciente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false) private String nome;
    @Column(nullable = false) private String email;
    private String telefone;
    private LocalDate dataNascimento;
    private String endereco;

    @OneToMany(mappedBy = "paciente")
    private final List<Consulta> consultas = new ArrayList<>();
}
