package com.luizjacomn.seeddesafiocdc.detalhecompra;

import com.luizjacomn.seeddesafiocdc.novopais.Pais;

public class PaisResponse {

    private final Long id;

    private final String nome;

    public PaisResponse(Pais pais) {
        this.id = pais.getId();
        this.nome = pais.getNome();
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

}
