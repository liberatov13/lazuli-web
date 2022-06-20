package br.com.liberato.lazuli.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "preco_receita")
public class PrecoReceita {

    @Id
    @GeneratedValue
    @Column(name = "id_preco_receita")
    private Integer idPrecoReceita;

    @ManyToOne
    @JoinColumn(name = "id_receita", referencedColumnName = "id_receita")
    private Receita receita;

    @ManyToOne
    @JoinColumn(name = "id_forma_venda", referencedColumnName = "id_forma_venda")
    private FormaVenda formaVenda;

    @Column(name = "preco")
    private Double preco;

    @Column(name = "dt_inicio_vigencia")
    private LocalDateTime dataInicioVigencia;

    @Column(name = "dt_fim_vigencia")
    private LocalDateTime dataFimVigencia;

    @Column(name = "preco_promocional")
    private Boolean isPromocional;

    @Column(name = "nome_promocao")
    private String nomePromocao;
}
