package com.luizjacomn.seeddesafiocdc.detalhecompra;

import com.luizjacomn.seeddesafiocdc.novacompra.ItemCompra;
import com.luizjacomn.seeddesafiocdc.novolivro.LivroResponse;

import java.math.BigDecimal;

public class ItemPedidoResponse {

    private final Integer quantidade;

    private final BigDecimal valor;

    private final LivroResponse livro;

    public ItemPedidoResponse(ItemCompra itemCompra) {
        this.quantidade = itemCompra.getQuantidade();
        this.valor = itemCompra.getValor();
        this.livro = new LivroResponse(itemCompra.getLivro());
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public LivroResponse getLivro() {
        return livro;
    }

    public BigDecimal getTotal() {
        return valor.multiply(BigDecimal.valueOf(quantidade));
    }

}
