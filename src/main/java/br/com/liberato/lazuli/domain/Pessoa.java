package br.com.liberato.lazuli.domain;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "pessoa")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Pessoa {

    @Id
    @GeneratedValue
    private Long idPessoa;

    @Column(nullable = false, length = 100)
    private String nome;

    @Column(unique = true, length = 100)
    private String email;

    @Column(name = "dt_nascimento")
    private LocalDate dataNascimento;

    @Column(length = 14, unique = true)
    private String documento;

    @Column(length = 150)
    private String endereco;
}
