package br.com.liberato.lazuli.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.time.LocalTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "receita")
public class Receita {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_receita")
    private Integer idReceita;


    @OneToOne
    @JoinColumn(name = "id_produto_final", referencedColumnName = "id_produto")
    private Produto produtoFinal;

    @Column(name = "tempo_preparo")
    private LocalTime tempoPreparo;

    @JsonManagedReference
    @OneToMany(mappedBy = "receita")
    @ToString.Exclude
    private List<PrecoReceita> precosReceita;

}
