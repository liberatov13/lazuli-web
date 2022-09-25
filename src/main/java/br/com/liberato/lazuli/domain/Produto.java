package br.com.liberato.lazuli.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "produto")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_produto")
    private Long idProduto;

    @NotBlank
    @Size(min = 2, max = 75)
    @Column(name = "descricao_basica", nullable = false, length = 75)
    private String descricaoBasica;

    @Size(max = 100)
    @Column(name = "descricao_completa", length = 100)
    private String descricaoCompleta;

    @ManyToOne
    @JoinColumn(name = "id_marca")
    private Marca marca;

    @Column(name = "cod_barras")
    private Long codigoBarras;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "id_tipo_produto", nullable = false)
    private TipoProduto tipoProduto;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "id_unidade_medida", nullable = false)
    private UnidadeMedida unidadeMedida;

    @Column(name = "qtd_estoque")
    private Double quantidadeEstoque;

    @Column(nullable = false, columnDefinition = "TINYINT(1)")
    private Boolean status;

    @JsonBackReference
    @ToString.Exclude
    @OneToOne(mappedBy = "produtoFinal")
    private Receita receita;

    @JsonBackReference
    @OrderBy("compra DESC")
    @OneToMany(mappedBy = "produto")
    private List<CompraProduto> comprasProduto;

    @JsonIgnore
    public Double getCustoMedio() {
        if (this.comprasProduto.isEmpty() || this.comprasProduto == null) {
            return null;
        }
        Double total = 0.0;
        for (CompraProduto compraProduto : this.comprasProduto) {
            total += compraProduto.getPrecoDaUnidade();
        }
        if (total == 0.0) {
            return null;
        }
        return (total / comprasProduto.size());
    }

}
