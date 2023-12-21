package com.luizjacomn.seeddesafiocdc.novacompra;

import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class NovoPedidoRequest {

    @NotNull
    @Positive
    private final BigDecimal total;

    @NotEmpty
    private final List<@Valid PedidoItemRequest> itens;

    public NovoPedidoRequest(@NotNull @Positive BigDecimal total, @NotEmpty List<@Valid PedidoItemRequest> itens) {
        this.total = total;
        this.itens = itens;
    }

    public List<PedidoItemRequest> getItens() {
        return itens;
    }

    public Function<Compra, Pedido> toModel(EntityManager entityManager) {
        return compra -> {
            var itemSet = itens.stream().map(item -> item.toModel(entityManager)).collect(Collectors.toSet());
            var pedido = new Pedido(compra, itemSet);

            Assert.isTrue(pedido.totalIgual(total), "Total informado não corresponde à soma dos itens!");
            return pedido;
        };
    }

}
