package br.com.zupacademy.william.ecommerce.venda;

import br.com.zupacademy.william.ecommerce.produto.Produto;
import br.com.zupacademy.william.ecommerce.usuario.Usuario;
import br.com.zupacademy.william.ecommerce.validation.annotation.ExistsId;
import br.com.zupacademy.william.ecommerce.venda.gateway.GatewayPagamento;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class VendaRequest {

    @NotNull
    @ExistsId(domainClass = Produto.class, fieldName = "id")
    private Long idProduto;

    @NotNull
    @Positive
    private Long quantidade;

    //TODO - Enum validator
    @JsonProperty("gateway_venda")
    @NotBlank
    private String gatewayVenda;

    public VendaRequest(Long idProduto, Long quantidade, String gatewayVenda) {
        this.idProduto = idProduto;
        this.quantidade = quantidade;
        this.gatewayVenda = gatewayVenda;
    }

    public Venda toModel(Produto produto, Usuario comprador) {
        return new Venda(GatewayPagamento.valueOf(this.gatewayVenda), produto, Math.toIntExact(this.quantidade), comprador);
    }

    public Long getIdProduto() {
        return idProduto;
    }

    public Long getQuantidade() {
        return quantidade;
    }
}
