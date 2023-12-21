package com.luizjacomn.seeddesafiocdc.detalhecompra;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.luizjacomn.seeddesafiocdc.novacompra.Compra;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class DetalheCompraResponse {

    private final String email;

    private final String nome;

    private final String sobrenome;

    private final String documento;

    private final String endereco;

    private final String complemento;

    private final String cidade;

    private final String telefone;

    private final String cep;

    private final PaisResponse pais;

    private final PedidoResponse pedido;

    private final BigDecimal valor;

    private final boolean temCupom;

    private EstadoResponse estado;

    @JsonProperty("valor_com_desconto")
    private BigDecimal valorComDesconto;

    public DetalheCompraResponse(Compra compra) {
        this.email = compra.getEmail();
        this.nome = compra.getNome();
        this.sobrenome = compra.getSobrenome();
        this.documento = compra.getDocumento();
        this.endereco = compra.getEndereco();
        this.complemento = compra.getComplemento();
        this.cidade = compra.getCidade();
        this.telefone = compra.getTelefone();
        this.cep = compra.getCep();
        this.pais = new PaisResponse(compra.getPais());
        this.pedido = new PedidoResponse(compra.getPedido());
        this.valor = pedido.getTotal();
        this.temCupom = compra.getCupomAplicado() != null;

        if (compra.getEstado() != null) {
            this.estado = new EstadoResponse(compra.getEstado());
        }

        if (temCupom) {
            var valorDescontoCupom = valor.multiply(compra.getCupomAplicado().getPercentualDesconto()).divide(BigDecimal.valueOf(100.0), RoundingMode.HALF_EVEN);
            this.valorComDesconto = valor.subtract(valorDescontoCupom).setScale(2, RoundingMode.HALF_EVEN);
        }
    }

    public String getEmail() {
        return email;
    }

    public String getNome() {
        return nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public String getDocumento() {
        return documento;
    }

    public String getEndereco() {
        return endereco;
    }

    public String getComplemento() {
        return complemento;
    }

    public String getCidade() {
        return cidade;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getCep() {
        return cep;
    }

    public EstadoResponse getEstado() {
        return estado;
    }

    public PaisResponse getPais() {
        return pais;
    }

    public PedidoResponse getPedido() {
        return pedido;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public boolean isTemCupom() {
        return temCupom;
    }

    public BigDecimal getValorComDesconto() {
        return valorComDesconto;
    }

}
