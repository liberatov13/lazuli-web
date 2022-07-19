package br.com.liberato.lazuli.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "compra")
public class Compra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_compra")
    private Long idCompra;

    @ManyToOne
    @JoinColumn(name = "id_fornecedor", nullable = false)
    private Fornecedor fornecedor;

    @Column(name = "vl_total", nullable = false)
    private Double valorTotal;

    @Column(name = "dt_compra", nullable = false)
    private LocalDateTime dataCompra;

    @JsonBackReference
    @OneToMany(mappedBy = "compra")
    @ToString.Exclude
    private List<CompraProduto> compraProduto;

}
