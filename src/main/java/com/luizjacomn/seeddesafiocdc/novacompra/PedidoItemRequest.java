package com.luizjacomn.seeddesafiocdc.novacompra;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.luizjacomn.seeddesafiocdc.novolivro.Livro;
import com.luizjacomn.seeddesafiocdc.validation.annotation.ExistsId;

import javax.persistence.EntityManager;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class PedidoItemRequest {

    @NotNull
    @ExistsId(Livro.class)
    @JsonProperty("livro_id")
    private final Long livroId;

    @NotNull
    @Min(1)
    private final Integer quantidade;

    public PedidoItemRequest(@NotNull Long livroId, @NotNull @Min(1) Integer quantidade) {
        this.livroId = livroId;
        this.quantidade = quantidade;
    }

    public Long getLivroId() {
        return livroId;
    }

    public ItemCompra toModel(EntityManager entityManager) {
        var livro = entityManager.find(Livro.class, livroId);

        return new ItemCompra(quantidade, livro);
    }

}
