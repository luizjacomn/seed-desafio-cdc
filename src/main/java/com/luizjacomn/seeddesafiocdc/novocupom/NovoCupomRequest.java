package com.luizjacomn.seeddesafiocdc.novocupom;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.luizjacomn.seeddesafiocdc.validation.annotation.UniqueValue;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class NovoCupomRequest {

    @NotBlank
    @UniqueValue(domainClass = Cupom.class, field = "codigo")
    private final String codigo;

    @NotNull
    @Positive
    @JsonProperty("percentual_desconto")
    private final BigDecimal percentualDesconto;

    @NotNull
    @Future
    @JsonProperty("expira_em")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private final LocalDateTime expiraEm;

    public NovoCupomRequest(@NotBlank String codigo, @NotNull @Positive BigDecimal percentualDesconto, @Future LocalDateTime expiraEm) {
        this.codigo = codigo;
        this.percentualDesconto = percentualDesconto;
        this.expiraEm = expiraEm;
    }

    public Cupom toModel() {
        return new Cupom(codigo, percentualDesconto, expiraEm);
    }
}
