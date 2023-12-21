package com.luizjacomn.seeddesafiocdc.novacompra;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Set;

@Entity
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private Compra compra;

    @ElementCollection
    private Set<ItemCompra> itens;

    @Deprecated
    public Pedido() { }

    public Pedido(Compra compra, Set<ItemCompra> itens) {
        this.compra = compra;
        this.itens = itens;
    }

    public Set<ItemCompra> getItens() {
        return itens;
    }

    public boolean totalIgual(BigDecimal total) {
        return itens.stream()
                .map(ItemCompra::getTotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add)
                .compareTo(total) == 0;
    }

}
