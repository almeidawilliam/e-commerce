package br.com.zupacademy.william.ecommerce.usuario;

import br.com.zupacademy.william.ecommerce.produto.Produto;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.*;

@Entity
public class Usuario implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String login;
    private String senha;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Perfil> perfis = new HashSet<>();

    @CreationTimestamp
    private LocalDateTime instanteCadastro = LocalDateTime.now();

    @OneToMany(mappedBy = "donoDoProduto", fetch = FetchType.EAGER)
    private List<Produto> produtos;

    public Usuario(String login, SenhaLimpa senhaLimpa) {
        this.login = login;
        this.senha = senhaLimpa.hash();
    }

    @Deprecated
    public Usuario() {
    }

    public boolean eDonoDoProduto(Produto produto) {
        return this.produtos.contains(produto);
    }

    public Long getId() {
        return id;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.perfis;
    }

    @Override
    public String getPassword() {
        return this.senha;
    }

    @Override
    public String getUsername() {
        return this.login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
