package br.com.zupacademy.william.ecommerce.produto.caracteristica;

import javax.validation.constraints.NotBlank;

public class ProdutoCaracteristicaInputDto {

    @NotBlank
    private String nome;

    @NotBlank
    private String descricao;

    public ProdutoCaracteristicaInputDto(String nome, String descricao) {
        this.nome = nome;
        this.descricao = descricao;
    }

    public ProdutoCaracteristica toModel() {
        return new ProdutoCaracteristica(this.nome, this.descricao);
    }



    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
