package br.com.liberato.lazuli.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "compra")
public class Compra {

    @Id
    @GeneratedValue
    private Long idCompra;

    @ManyToMany
    @JoinTable(name = "compra_produto",
            joinColumns = {@JoinColumn(name = "id_compra")},
            inverseJoinColumns = {@JoinColumn(name = "id_produto")})
    private List<Produto> produtos;

    @ManyToOne
    @JoinColumn(name = "id_fornecedor", nullable = false)
    private Fornecedor fornecedor;

    @Column(name = "vl_total", nullable = false)
    private Double valorTotal;

    @Column(name = "dt_compra", nullable = false)
    private LocalDate dataCompra;

}
