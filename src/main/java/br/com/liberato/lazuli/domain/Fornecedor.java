package br.com.liberato.lazuli.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "fornecedor")
public class Fornecedor {

    @Id
    @GeneratedValue
    private Long idFornecedor;

    @OneToOne
    @JoinColumn(name = "id_pessoa", nullable = false)
    private Pessoa pessoa;

}
