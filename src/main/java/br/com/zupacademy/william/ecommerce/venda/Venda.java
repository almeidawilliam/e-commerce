package br.com.zupacademy.william.ecommerce.venda;

import br.com.zupacademy.william.ecommerce.produto.Produto;
import br.com.zupacademy.william.ecommerce.usuario.Usuario;
import br.com.zupacademy.william.ecommerce.venda.gateway.GatewayPagamento;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
public class Venda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private StatusVenda statusVenda = StatusVenda.INICIADA;

    @Enumerated(EnumType.STRING)
    private GatewayPagamento gatewayPagamento;

//    @ManyToMany
//    @JoinTable(name = "venda_produto",
//    joinColumns = @JoinColumn(name = "id_venda"),
//    inverseJoinColumns = @JoinColumn(name ="id_produto"))
//    private List<Produto> produtos;

    private int quantidade;

    @ManyToOne
    @JoinColumn(name = "id_produto")
    private Produto produto;

    @ManyToOne
    private Usuario comprador;

    private BigDecimal valor;

    public Venda(GatewayPagamento gatewayPagamento, Produto produto, int quantidade, Usuario comprador) {
        this.gatewayPagamento = gatewayPagamento;
        this.produto = produto;
        this.quantidade = quantidade;
        this.comprador = comprador;
        this.valor = produto.getValor().multiply(BigDecimal.valueOf(quantidade));
    }

    public StatusVenda getStatusVenda() {
        return statusVenda;
    }

    public GatewayPagamento getGatewayVenda() {
        return gatewayPagamento;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public Usuario getComprador() {
        return comprador;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public String getEmailDoComprador() {
        return this.comprador.getEmail();
    }

    public Produto getProduto() {
        return produto;
    }

    public String getUrlDeRetorno() {
        return this.gatewayPagamento.getUrlRetorno(this.id);
    }
}
