package com.luizjacomn.seeddesafiocdc.novacategoria;

import com.luizjacomn.seeddesafiocdc.validation.annotation.UniqueValue;

import javax.validation.constraints.NotBlank;

public class NovaCategoriaRequest {

    @NotBlank
    @UniqueValue(domainClass = Categoria.class, field = "nome")
    private String nome;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Categoria toModel() {
        return new Categoria(this.nome);
    }

}
