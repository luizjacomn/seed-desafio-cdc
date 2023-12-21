package com.luizjacomn.seeddesafiocdc.novacompra;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.luizjacomn.seeddesafiocdc.novoestado.Estado;
import com.luizjacomn.seeddesafiocdc.novopais.Pais;
import com.luizjacomn.seeddesafiocdc.validation.annotation.ExistsId;
import com.luizjacomn.seeddesafiocdc.validation.groups.PessoaFisica;
import com.luizjacomn.seeddesafiocdc.validation.groups.PessoaJuridica;
import com.luizjacomn.seeddesafiocdc.validation.groups.PessoaSequenceGroupProvider;
import org.hibernate.validator.constraints.br.CNPJ;
import org.hibernate.validator.constraints.br.CPF;
import org.hibernate.validator.group.GroupSequenceProvider;

import javax.persistence.EntityManager;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@GroupSequenceProvider(PessoaSequenceGroupProvider.class)
public class NovaCompraRequest {

    @NotBlank
    @Email
    private final String email;

    @NotBlank
    private final String nome;

    @NotBlank
    private final String sobrenome;

    @NotBlank
    @CPF(groups = PessoaFisica.class)
    @CNPJ(groups = PessoaJuridica.class)
    private final String documento;

    @NotBlank
    private final String endereco;

    @NotBlank
    private final String complemento;

    @NotBlank
    private final String cidade;

    @JsonProperty("estado_id")
    @ExistsId(Estado.class)
    private final Long estadoId;

    @JsonProperty("pais_id")
    @ExistsId(Pais.class)
    private final Long paisId;

    @NotBlank
    private final String telefone;

    @NotBlank
    private final String cep;

    @NotNull
    @Valid
    private final NovoPedidoRequest pedido;

    public NovaCompraRequest(@NotBlank @Email String email, @NotBlank String nome, @NotBlank String sobrenome, @NotBlank @CPF(groups = PessoaFisica.class) @CNPJ(groups = PessoaJuridica.class) String documento, @NotBlank String endereco, @NotBlank String complemento, @NotBlank String cidade, Long estadoId, Long paisId, @NotBlank String telefone, @NotBlank String cep, @NotNull @Valid NovoPedidoRequest pedido) {
        this.email = email;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.documento = documento;
        this.endereco = endereco;
        this.complemento = complemento;
        this.cidade = cidade;
        this.estadoId = estadoId;
        this.paisId = paisId;
        this.telefone = telefone;
        this.cep = cep;
        this.pedido = pedido;
    }

    public String getDocumento() {
        return documento;
    }

    public Long getEstadoId() {
        return estadoId;
    }

    public Long getPaisId() {
        return paisId;
    }

    public NovoPedidoRequest getPedido() {
        return pedido;
    }

    public Compra toModel(EntityManager entityManager) {
        Estado estado = null;
        if (estadoId != null) {
            estado = entityManager.find(Estado.class, estadoId);
        }

        var pais = entityManager.find(Pais.class, paisId);

        var gerarPedido = pedido.toModel(entityManager);

        return new Compra(email, nome, sobrenome, documento, endereco, complemento, cidade, estado, pais, telefone, cep, gerarPedido);
    }

}
