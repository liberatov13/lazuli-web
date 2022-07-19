package br.com.liberato.lazuli.domain;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "compra_produto")
public class CompraProduto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_compra_produto")
    private Long idCompraProduto;

    @ManyToOne
    @JoinColumn(name = "id_compra", referencedColumnName = "id_compra")
    private Compra compra;

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "id_produto", referencedColumnName = "id_produto")
    private Produto produto;

    @Column(name = "qtd_comprada")
    private Double quantidadeComprada;

    @Column(name = "vl_unidade")
    private Double precoDaUnidade;

}
