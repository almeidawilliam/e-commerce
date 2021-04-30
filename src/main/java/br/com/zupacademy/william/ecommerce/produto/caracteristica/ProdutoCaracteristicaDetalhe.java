package br.com.zupacademy.william.ecommerce.produto.caracteristica;

public class ProdutoCaracteristicaDetalhe {

    private String nome;
    private String descricao;

    public ProdutoCaracteristicaDetalhe(ProdutoCaracteristica produtoCaracteristica) {
        this.nome = produtoCaracteristica.getNome();
        this.descricao = produtoCaracteristica.getDescricao();
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }
}
