package br.com.zupacademy.william.ecommerce.produto;

import br.com.zupacademy.william.ecommerce.categoria.Categoria;
import br.com.zupacademy.william.ecommerce.produto.caracteristica.ProdutoCaracteristica;
import br.com.zupacademy.william.ecommerce.usuario.Usuario;
import io.jsonwebtoken.lang.Assert;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private BigDecimal valor;
    private int quantidadeDisponivel;
    private String descricao;

    @OneToMany(cascade = CascadeType.ALL)
    private Set<ProdutoCaracteristica> caracteristicas;

    @ManyToOne
    @JoinColumn(name = "id_categoria")
    private Categoria categoria;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario donoDoProduto;

    @CreationTimestamp
    private LocalDateTime instanteCriacao = LocalDateTime.now();

    public Produto(String nome, BigDecimal valor, int quantidadeDisponivel, String descricao,
                   Set<ProdutoCaracteristica> caracteristicas, Categoria categoria, Usuario donoDoProduto) {
        this.nome = nome;
        this.valor = valor;
        this.quantidadeDisponivel = quantidadeDisponivel;
        this.descricao = descricao;
        this.caracteristicas = caracteristicas;
        this.categoria = categoria;
        this.donoDoProduto = donoDoProduto;

        Assert.isTrue(this.caracteristicas.size() >= 3, "É necessário ao menos 3 características para um produto");
    }
}
