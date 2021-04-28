package br.com.zupacademy.william.ecommerce.produto.caracteristica;

import br.com.zupacademy.william.ecommerce.produto.Produto;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class ProdutoCaracteristica {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String descricao;

    @ManyToOne
    @JoinColumn(name = "id_produto")
    private Produto produto;

    public ProdutoCaracteristica(String nome, String descricao) {
        this.nome = nome;
        this.descricao = descricao;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProdutoCaracteristica that = (ProdutoCaracteristica) o;
        return nome.equals(that.nome);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome);
    }
}
