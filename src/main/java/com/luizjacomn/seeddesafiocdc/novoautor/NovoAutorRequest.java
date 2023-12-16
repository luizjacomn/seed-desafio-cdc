package com.luizjacomn.seeddesafiocdc.novoautor;

import com.luizjacomn.seeddesafiocdc.validation.annotation.UniqueValue;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class NovoAutorRequest {

    @NotBlank
    private String nome;

    @Email
    @NotBlank
    @UniqueValue(domainClass = Autor.class, field = "email")
    private String email;

    @Length(max = 400)
    @NotBlank
    private String descricao;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Autor toModel() {
        return new Autor(this.nome, this.email, this.descricao);
    }
}
