package br.com.liberato.lazuli.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "produto")
public class Produto {

    @Id
    @GeneratedValue
    @Column(name = "id_produto")
    private Long idProduto;

    @Column(name = "descricao_basica", nullable = false, length = 75)
    private String descricaoBasica;

    @Column(name = "descricao_completa", length = 100)
    private String descicaoCompleta;

    @ManyToOne
    @JoinColumn(name = "id_marca")
    private Marca marca;

    @Column(name = "cod_barras")
    private Long codigoBarras;

    @ManyToOne
    @JoinColumn(name = "id_tipo_produto", nullable = false)
    private TipoProduto tipoProduto;

    @ManyToOne
    @JoinColumn(name = "id_unidade_medida", nullable = false)
    private UnidadeMedida unidadeMedida;

    @Column(name = "qtd_estoque")
    private Double quantidadeEstoque;

    @Column(nullable = false, columnDefinition = "TINYINT(1)")
    private Boolean status;

    @JsonBackReference
    @OneToOne(mappedBy = "produtoFinal")
    private Receita receita;

}
