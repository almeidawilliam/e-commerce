package br.com.zupacademy.william.ecommerce.produto;

import br.com.zupacademy.william.ecommerce.produto.avaliacao.ProdutoAvaliacao;
import br.com.zupacademy.william.ecommerce.produto.avaliacao.ProdutoAvaliacaoDetalhe;
import br.com.zupacademy.william.ecommerce.produto.caracteristica.ProdutoCaracteristicaDetalhe;
import br.com.zupacademy.william.ecommerce.produto.pergunta.ProdutoPerguntaDetalhe;

import java.util.Set;
import java.util.stream.Collectors;

public class ProdutoDetalhesResponse {

    private ProdutoDetalheDto produto;
    private Set<ProdutoCaracteristicaDetalhe> caracteristicas;
    private Set<String> imagens;
    private double mediaNotas;
    private int totalNotas;
    private Set<ProdutoPerguntaDetalhe> perguntas;
    private Set<ProdutoAvaliacaoDetalhe> avaliacoes;

    public ProdutoDetalhesResponse(Produto produto) {
        this.produto = new ProdutoDetalheDto(produto);
        this.caracteristicas = produto.getCaracteristicas()
                .stream()
                .map(ProdutoCaracteristicaDetalhe::new)
                .collect(Collectors.toSet());
        this.imagens = produto.getLinksImagens();
        this.perguntas = produto.getPerguntas()
                .stream()
                .map(ProdutoPerguntaDetalhe::new)
                .collect(Collectors.toSet());
        this.avaliacoes = produto.getAvaliacoes()
                .stream()
                .map(ProdutoAvaliacaoDetalhe::new)
                .collect(Collectors.toSet());
        this.mediaNotas = produto.getAvaliacoes()
                .stream()
                .mapToInt(ProdutoAvaliacao::getNota)
                .average()
                .orElse(0);
        this.totalNotas = produto.getAvaliacoes().size();
    }

    public ProdutoDetalheDto getProduto() {
        return produto;
    }

    public Set<ProdutoCaracteristicaDetalhe> getCaracteristicas() {
        return caracteristicas;
    }

    public Set<String> getImagens() {
        return imagens;
    }

    public double getMediaNotas() {
        return mediaNotas;
    }

    public int getTotalNotas() {
        return totalNotas;
    }

    public Set<ProdutoPerguntaDetalhe> getPerguntas() {
        return perguntas;
    }

    public Set<ProdutoAvaliacaoDetalhe> getAvaliacoes() {
        return avaliacoes;
    }
}
