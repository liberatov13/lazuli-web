package br.com.liberato.lazuli.domain;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "grupo_acesso")
public class GrupoAcesso implements GrantedAuthority {

    @Id
    @GeneratedValue
    Long idGrupoAcesso;

    @Column(nullable = false, length = 75)
    String nome;

    @Column(nullable = false, columnDefinition = "TINYINT(1)")
    Boolean status;


    @Override
    public String getAuthority() {
        return null;
    }
}
