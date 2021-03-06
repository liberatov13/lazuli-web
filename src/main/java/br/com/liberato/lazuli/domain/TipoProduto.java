package br.com.liberato.lazuli.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tipo_produto")
public class TipoProduto {

    @Id
    @GeneratedValue
    @Column(name = "id_tipo_produto")
    private Long idTipoProduto;

    @Column(nullable = false)
    private String nome;

}
