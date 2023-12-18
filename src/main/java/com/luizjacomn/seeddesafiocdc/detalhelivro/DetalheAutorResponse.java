package com.luizjacomn.seeddesafiocdc.detalhelivro;

import com.luizjacomn.seeddesafiocdc.novoautor.Autor;

public class DetalheAutorResponse {

    private final String nome;
    private final String descricao;

    public DetalheAutorResponse(Autor autor) {
        this.nome = autor.getNome();
        this.descricao = autor.getDescricao();
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }
}
