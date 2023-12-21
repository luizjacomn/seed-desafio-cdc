package com.luizjacomn.seeddesafiocdc.novocupom;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Embeddable
public class CupomAplicado {

    @ManyToOne
    @JoinColumn(name = "cupom_id")
    private Cupom cupom;

    @Column(name = "percentual_desconto", nullable = false)
    private BigDecimal percentualDesconto;

    private LocalDateTime dataExpiracao;

    @Deprecated
    public CupomAplicado() {}

    public CupomAplicado(Cupom cupom) {
        this.cupom = cupom;
        this.percentualDesconto = cupom.getPercentualDesconto();
        this.dataExpiracao = cupom.getDataExpiracao();
    }

    public Cupom getCupom() {
        return cupom;
    }

    public BigDecimal getPercentualDesconto() {
        return percentualDesconto;
    }

    public LocalDateTime getDataExpiracao() {
        return dataExpiracao;
    }

}
