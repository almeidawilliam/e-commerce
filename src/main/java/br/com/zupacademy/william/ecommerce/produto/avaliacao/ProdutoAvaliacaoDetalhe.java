package br.com.zupacademy.william.ecommerce.produto.avaliacao;

public class ProdutoAvaliacaoDetalhe {

    private String avaliador;
    private String titulo;
    private String descricao;
    private int nota;

    public ProdutoAvaliacaoDetalhe(ProdutoAvaliacao produtoAvaliacao) {
        this.avaliador = produtoAvaliacao.getEmailAvaliador();
        this.titulo = produtoAvaliacao.getTitulo();
        this.descricao = produtoAvaliacao.getDescricao();
        this.nota = produtoAvaliacao.getNota();
    }

    public String getAvaliador() {
        return avaliador;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public int getNota() {
        return nota;
    }
}
