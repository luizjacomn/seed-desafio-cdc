package com.luizjacomn.seeddesafiocdc.novolivro;

public class LivroResponse {

    private final Long id;

    private final String titulo;

    public LivroResponse(Livro livro) {
        this.id = livro.getId();
        this.titulo = livro.getTitulo();
    }

    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

}
