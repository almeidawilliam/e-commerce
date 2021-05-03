package br.com.zupacademy.william.ecommerce.produto;

import br.com.zupacademy.william.ecommerce.categoria.Categoria;
import br.com.zupacademy.william.ecommerce.produto.avaliacao.ProdutoAvaliacao;
import br.com.zupacademy.william.ecommerce.produto.caracteristica.ProdutoCaracteristica;
import br.com.zupacademy.william.ecommerce.produto.imagem.ProdutoImagem;
import br.com.zupacademy.william.ecommerce.produto.pergunta.ProdutoPergunta;
import br.com.zupacademy.william.ecommerce.usuario.Usuario;
import io.jsonwebtoken.lang.Assert;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

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
    @JoinColumn(name = "idProduto")
    private Set<ProdutoCaracteristica> caracteristicas;

    @ManyToOne
    @JoinColumn(name = "id_categoria")
    private Categoria categoria;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario donoDoProduto;

    @CreationTimestamp
    private LocalDateTime instanteCriacao = LocalDateTime.now();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "produto")
    private Set<ProdutoImagem> imagens;

    @OneToMany(mappedBy = "produto")
    private Set<ProdutoPergunta> perguntas;

    @OneToMany(mappedBy = "produto")
    private Set<ProdutoAvaliacao> avaliacoes;

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

    @Deprecated
    public Produto() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Produto produto = (Produto) o;
        return Objects.equals(id, produto.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, valor, quantidadeDisponivel, descricao, caracteristicas, categoria, donoDoProduto, instanteCriacao, imagens);
    }

    public void associarImagens(Set<String> links) {
        this.imagens.addAll(links.stream()
                .map(link -> new ProdutoImagem(this, link))
                .collect(Collectors.toSet()));
    }

    public String getNome() {
        return nome;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public String getDescricao() {
        return descricao;
    }

    public Set<ProdutoCaracteristica> getCaracteristicas() {
        return caracteristicas;
    }

    public Set<String> getLinksImagens() {
        return imagens.stream()
                .map(ProdutoImagem::getLink)
                .collect(Collectors.toSet());
    }

    public Set<ProdutoPergunta> getPerguntas() {
        return perguntas;
    }

    public Usuario getDonoDoProduto() {
        return this.donoDoProduto;
    }

    public String getEmailDoDonoDoProduto() {
        return this.donoDoProduto.getEmail();
    }

    public Set<ProdutoAvaliacao> getAvaliacoes() {
        return avaliacoes;
    }

    public boolean possuiQuantidadeEmEstoque(Long quantidade) {
        return this.quantidadeDisponivel >= quantidade;
    }

    public void abateDoEstoque(Long quantidade) {
        this.quantidadeDisponivel -= quantidade;
    }
}
