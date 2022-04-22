package br.com.liberato.lazuli.domain;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.List;

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
    private Long idGrupoAcesso;

    @Column(nullable = false, length = 75)
    private String nome;

    @Column(nullable = false, columnDefinition = "TINYINT(1)")
    private Boolean status;

    @ToString.Exclude
    @ManyToMany(mappedBy = "gruposAcessos", fetch = FetchType.EAGER)
    private List<Usuario> usuarios;


    @Override
    public String getAuthority() {
        return null;
    }
}
