package com.luizjacomn.seeddesafiocdc.novocupom;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
public class Cupom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String codigo;

    @Column(name = "percentual_desconto", nullable = false)
    private BigDecimal percentualDesconto;

    @Column(name = "data_expiracao", nullable = false)
    private LocalDateTime dataExpiracao;

    @Deprecated
    public Cupom() { }

    public Cupom(String codigo, BigDecimal percentualDesconto, LocalDateTime dataExpiracao) {
        this.codigo = codigo;
        this.percentualDesconto = percentualDesconto;
        this.dataExpiracao = dataExpiracao;
    }

    public Long getId() {
        return id;
    }

    public String getCodigo() {
        return codigo;
    }

    public BigDecimal getPercentualDesconto() {
        return percentualDesconto;
    }

    public LocalDateTime getDataExpiracao() {
        return dataExpiracao;
    }

    public boolean valido() {
        return !LocalDateTime.now().isAfter(dataExpiracao);
    }

}
