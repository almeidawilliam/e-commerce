package br.com.zupacademy.william.ecommerce.venda;

import br.com.zupacademy.william.ecommerce.email.Email;

public class EmailVenda implements Email {

    private static final String remetente = "ecommerce@ecomerce.com";

    private String titulo = "Pedido de venda de %s unidades do produto %s, no valor de %s";
    private String destinatario;

    public EmailVenda(Venda venda) {
        this.titulo = String.format(titulo, venda.getQuantidade(), venda.getProduto().getNome(), venda.getValor());
        this.destinatario = venda.getEmailDoComprador();
    }

    @Override
    public String getTitulo() {
        return this.titulo;
    }

    @Override
    public String getRemetente() {
        return remetente;
    }

    @Override
    public String getDestinatario() {
        return this.destinatario;
    }
}
