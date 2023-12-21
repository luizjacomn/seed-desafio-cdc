package com.luizjacomn.seeddesafiocdc.detalhecompra;

import com.luizjacomn.seeddesafiocdc.novacompra.ItemCompra;
import com.luizjacomn.seeddesafiocdc.novacompra.Pedido;

import java.math.BigDecimal;
import java.util.Set;
import java.util.stream.Collectors;

public class PedidoResponse {

    private final BigDecimal total;

    private final Set<ItemPedidoResponse> itens;

    public PedidoResponse(Pedido pedido) {
        this.total = pedido.getItens().stream().map(ItemCompra::getTotal).reduce(BigDecimal.ZERO, BigDecimal::add);
        this.itens = pedido.getItens().stream().map(ItemPedidoResponse::new).collect(Collectors.toSet());
    }

    public BigDecimal getTotal() {
        return total;
    }

    public Set<ItemPedidoResponse> getItens() {
        return itens;
    }

}
