package com.luizjacomn.seeddesafiocdc.novoestado;

import com.luizjacomn.seeddesafiocdc.novopais.Pais;
import com.luizjacomn.seeddesafiocdc.validation.annotation.ExistsId;
import com.luizjacomn.seeddesafiocdc.validation.annotation.UniqueValue;

import javax.persistence.EntityManager;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class NovoEstadoRequest {

    @NotBlank
    @UniqueValue(domainClass = Estado.class, field = "nome")
    private String nome;

    @NotNull
    @ExistsId(Pais.class)
    private Long paisId;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Long getPaisId() {
        return paisId;
    }

    public void setPaisId(Long paisId) {
        this.paisId = paisId;
    }

    public Estado toModel(EntityManager entityManager) {
        var pais = entityManager.find(Pais.class, this.paisId);

        return new Estado(this.nome, pais);
    }

}
