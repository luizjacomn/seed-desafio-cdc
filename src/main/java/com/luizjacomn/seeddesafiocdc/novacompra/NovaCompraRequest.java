package com.luizjacomn.seeddesafiocdc.novacompra;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.luizjacomn.seeddesafiocdc.novocupom.Cupom;
import com.luizjacomn.seeddesafiocdc.novocupom.CupomRepository;
import com.luizjacomn.seeddesafiocdc.novoestado.Estado;
import com.luizjacomn.seeddesafiocdc.novopais.Pais;
import com.luizjacomn.seeddesafiocdc.validation.annotation.ExistsId;
import com.luizjacomn.seeddesafiocdc.validation.annotation.ExistsProperty;
import com.luizjacomn.seeddesafiocdc.validation.groups.PessoaFisica;
import com.luizjacomn.seeddesafiocdc.validation.groups.PessoaJuridica;
import com.luizjacomn.seeddesafiocdc.validation.groups.PessoaSequenceGroupProvider;
import org.hibernate.validator.constraints.br.CNPJ;
import org.hibernate.validator.constraints.br.CPF;
import org.hibernate.validator.group.GroupSequenceProvider;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Optional;

@GroupSequenceProvider(PessoaSequenceGroupProvider.class)
// 9
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
    private Long estadoId;

    @JsonProperty("pais_id")
    @ExistsId(Pais.class)
    private final Long paisId;

    @NotBlank
    private final String telefone;

    @NotBlank
    private final String cep;

    // 1
    @NotNull
    @Valid
    private final NovoPedidoRequest pedido;

    @ExistsProperty(domainClass = Cupom.class, field = "codigo")
    private String codigoCupom;

    public NovaCompraRequest(@NotBlank @Email String email, @NotBlank String nome, @NotBlank String sobrenome, @NotBlank @CPF(groups = PessoaFisica.class) @CNPJ(groups = PessoaJuridica.class) String documento, @NotBlank String endereco, @NotBlank String complemento, @NotBlank String cidade, Long paisId, @NotBlank String telefone, @NotBlank String cep, @NotNull @Valid NovoPedidoRequest pedido) {
        this.email = email;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.documento = documento;
        this.endereco = endereco;
        this.complemento = complemento;
        this.cidade = cidade;
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

    public void setEstadoId(Long estadoId) {
        this.estadoId = estadoId;
    }

    public Long getPaisId() {
        return paisId;
    }

    public NovoPedidoRequest getPedido() {
        return pedido;
    }

    public void setCodigoCupom(String codigoCupom) {
        this.codigoCupom = codigoCupom;
    }

    public Optional<String> getCodigoCupom() {
        return Optional.ofNullable(codigoCupom);
    }

    // 1
    public Compra toModel(EntityManager entityManager, CupomRepository cupomRepository) {
        // 1
        var pais = entityManager.find(Pais.class, paisId);

        // 1
        var gerarPedido = pedido.toModel(entityManager);

        // 1
        // 1 - passar função como argumento
        var compra = new Compra(email, nome, sobrenome, documento, endereco, complemento, cidade, pais, telefone, cep, gerarPedido);

        // 1
        if (estadoId != null) {
            compra.setEstado(entityManager.find(Estado.class, estadoId));
        }

        // 1
        if (StringUtils.hasText(codigoCupom)) {
            // 1 - lançar exception (orElseThrow)
            var cupom = cupomRepository.findByCodigo(codigoCupom).orElseThrow();
            compra.aplicaCupom(cupom);
        }

        return compra;
    }

}
