package br.com.zupacademy.william.ecommerce.produto.pergunta;

import java.time.LocalDateTime;

public class ProdutoPerguntaDetalhe {

    private String titulo;
    private String usuario;
    private LocalDateTime instanteCriacao;

    public ProdutoPerguntaDetalhe(ProdutoPergunta produtoPergunta) {
        this.titulo = produtoPergunta.getTitulo();
        this.usuario = produtoPergunta.getEmailDoCurioso();
        this.instanteCriacao = produtoPergunta.getInstanteCriacao();
    }

    public String getTitulo() {
        return titulo;
    }

    public String getUsuario() {
        return usuario;
    }

    public LocalDateTime getInstanteCriacao() {
        return instanteCriacao;
    }
}
