package com.luizjacomn.seeddesafiocdc.detalhecompra;

import com.luizjacomn.seeddesafiocdc.novoestado.Estado;

public class EstadoResponse {

    private final Long id;

    private final String nome;

    public EstadoResponse(Estado estado) {
        this.id = estado.getId();
        this.nome = estado.getNome();
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

}
