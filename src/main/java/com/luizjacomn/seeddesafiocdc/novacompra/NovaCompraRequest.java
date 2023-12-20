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
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@GroupSequenceProvider(PessoaSequenceGroupProvider.class)
public class NovaCompraRequest {

    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String nome;

    @NotBlank
    private String sobrenome;

    @NotBlank
    @CPF(groups = PessoaFisica.class)
    @CNPJ(groups = PessoaJuridica.class)
    private String documento;

    @NotBlank
    private String endereco;

    @NotBlank
    private String complemento;

    @NotBlank
    private String cidade;

    @JsonProperty("estado_id")
    @ExistsId(Estado.class)
    private Long estadoId;

    @JsonProperty("pais_id")
    @ExistsId(Pais.class)
    private Long paisId;

    @NotBlank
    private String telefone;

    @NotBlank
    private String cep;

    public void setEmail(String email) {
        this.email = email;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public Long getEstadoId() {
        return estadoId;
    }

    public void setEstadoId(Long estadoId) {
        this.estadoId = estadoId;
    }

    public Long getPaisId() {
        return paisId;
    }

    public void setPaisId(Long paisId) {
        this.paisId = paisId;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public Compra toModel(EntityManager entityManager) {
        Estado estado = null;
        if (estadoId != null) {
            estado = entityManager.find(Estado.class, estadoId);
        }

        var pais = entityManager.find(Pais.class, paisId);

        return new Compra(email, nome, sobrenome, documento, endereco, complemento, cidade, estado, pais, telefone, cep);
    }

}
