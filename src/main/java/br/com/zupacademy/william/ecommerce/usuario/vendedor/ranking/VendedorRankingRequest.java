package br.com.zupacademy.william.ecommerce.usuario.vendedor.ranking;

import javax.validation.constraints.NotBlank;

public class VendedorRankingRequest {

    @NotBlank
    private Long idVenda;

    public VendedorRankingRequest(Long idVenda) {
        this.idVenda = idVenda;
    }

    public Long getIdVenda() {
        return idVenda;
    }
}
