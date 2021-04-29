package br.com.zupacademy.william.ecommerce.produto.pergunta;

import br.com.zupacademy.william.ecommerce.produto.Produto;
import br.com.zupacademy.william.ecommerce.usuario.Usuario;

import javax.validation.constraints.NotBlank;

public class ProdutoPerguntaRequestDto {

    @NotBlank
    private String titulo;

    /**
     * Bug do jackson, nao aceitou o construtor com um parametro nem setTitulo
     */
    @Deprecated
    public ProdutoPerguntaRequestDto() {
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public ProdutoPergunta toModel(Produto produto, Usuario usuario) {
        return new ProdutoPergunta(this.titulo, produto, usuario);
    }


}
