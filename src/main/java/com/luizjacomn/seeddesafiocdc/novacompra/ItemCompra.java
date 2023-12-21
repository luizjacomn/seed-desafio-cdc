package com.luizjacomn.seeddesafiocdc.novacompra;

import com.luizjacomn.seeddesafiocdc.novolivro.Livro;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;
import java.util.Objects;

@Embeddable
public class ItemCompra {

    @Column(nullable = false)
    private Integer quantidade;

    @Column(nullable = false)
    private BigDecimal valor;

    @ManyToOne
    @JoinColumn(name = "livro_id", nullable = false)
    private Livro livro;

    @Deprecated
    public ItemCompra() { }

    public ItemCompra(Integer quantidade, Livro livro) {
        this.quantidade = quantidade;
        this.valor = livro.getPreco();
        this.livro = livro;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public BigDecimal getTotal() {
        return valor.multiply(BigDecimal.valueOf(quantidade));
    }

    public Livro getLivro() {
        return livro;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ItemCompra that = (ItemCompra) o;
        return livro.equals(that.livro);
    }

    @Override
    public int hashCode() {
        return Objects.hash(livro);
    }
}
