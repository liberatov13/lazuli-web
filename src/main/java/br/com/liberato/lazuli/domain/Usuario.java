package br.com.liberato.lazuli.domain;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "usuario")
public class Usuario {

    @Id
    @GeneratedValue
    private Long idUsuario;

    @OneToOne
    @JoinColumn(name = "id_pessoa")
    private Pessoa pessoa;

    @Column(name = "nome_usuario", nullable = false, length = 45)
    private String nomeUsuario;

    @Column(name = "senha", length = 100)
    private String senha;

    @Column(nullable = false)
    private Boolean status;

}
