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
@Table(name = "marca")
public class Marca {

    @Id
    @GeneratedValue
    private Long idMarca;

    @Column(nullable = false, length = 75)
    private String nome;

    @Column(nullable = false, columnDefinition = "TINYINT(1)")
    private Boolean status;

}
