package br.com.zupacademy.william.ecommerce.produto;

import br.com.zupacademy.william.ecommerce.categoria.Categoria;
import br.com.zupacademy.william.ecommerce.produto.caracteristica.ProdutoCaracteristica;
import br.com.zupacademy.william.ecommerce.produto.caracteristica.ProdutoCaracteristicaInputDto;
import br.com.zupacademy.william.ecommerce.usuario.Usuario;
import br.com.zupacademy.william.ecommerce.validation.annotation.ExistsId;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.Length;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class ProdutoInputDto {

    @NotBlank
    private String nome;

    @NotNull
    @Positive
    private BigDecimal valor;

    @NotNull
    @PositiveOrZero
    @JsonProperty("quantidade_disponivel")
    private int quantidadeDisponivel;

    @NotBlank
    @Length(max = 1000)
    private String descricao;

    @Size(min = 3, max = 100)
    @Valid
    private List<ProdutoCaracteristicaInputDto> caracteristicas;

    @ExistsId(domainClass = Categoria.class, fieldName = "id")
    @JsonProperty("id_categoria")
    private Long idCategoria;

    public ProdutoInputDto(String nome, BigDecimal valor, int quantidadeDisponivel, String descricao, List<ProdutoCaracteristicaInputDto> produtoCaracteristicasInputDto, Long idCategoria) {
        this.nome = nome;
        this.valor = valor;
        this.quantidadeDisponivel = quantidadeDisponivel;
        this.descricao = descricao;
        this.caracteristicas = produtoCaracteristicasInputDto;
        this.idCategoria = idCategoria;
    }

    public Produto toModel(Categoria categoriaDoProduto, Usuario donoDoProduto) {
        Set<ProdutoCaracteristica> crodutoCaracteristicas = new HashSet<>();

        if (!this.caracteristicas.isEmpty()) {
            crodutoCaracteristicas.addAll(this.caracteristicas.stream()
                    .map(ProdutoCaracteristicaInputDto::toModel)
                    .collect(Collectors.toList()));
        }

        return new Produto(
                this.nome,
                this.valor,
                this.quantidadeDisponivel,
                this.descricao,
                crodutoCaracteristicas,
                categoriaDoProduto,
                donoDoProduto);
    }

    public Long getIdCategoria() {
        return idCategoria;
    }

    public List<ProdutoCaracteristicaInputDto> getCaracteristicas() {
        return caracteristicas;
    }

    public boolean temCaracteristicasRepetidas() {
        List<String> caracteristicasDepoisDoDistinct = this.caracteristicas.stream()
                .map(ProdutoCaracteristicaInputDto::getNome)
                .distinct()
                .collect(Collectors.toList());

        return (caracteristicasDepoisDoDistinct.size() != this.caracteristicas.size());
    }
}
