package com.luizjacomn.seeddesafiocdc.novacompra;

import com.luizjacomn.seeddesafiocdc.novoestado.Estado;
import com.luizjacomn.seeddesafiocdc.novopais.Pais;

import javax.persistence.*;
import java.util.function.Function;

@Entity
public class Compra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String sobrenome;

    @Column(nullable = false)
    private String documento;

    @Column(nullable = false)
    private String endereco;

    @Column(nullable = false)
    private String complemento;

    @Column(nullable = false)
    private String cidade;

    @ManyToOne
    @JoinColumn(name = "estado_id")
    private Estado estado;

    @ManyToOne
    @JoinColumn(name = "pais_id", nullable = false)
    private Pais pais;

    private String telefone;

    private String cep;

    @OneToOne(mappedBy = "compra", cascade = CascadeType.PERSIST)
    private Pedido pedido;

    @Deprecated
    public Compra() { }

    public Compra(String email, String nome, String sobrenome, String documento, String endereco, String complemento, String cidade, Estado estado, Pais pais, String telefone, String cep, Function<Compra, Pedido> gerarPedido) {
        this.email = email;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.documento = documento;
        this.endereco = endereco;
        this.complemento = complemento;
        this.cidade = cidade;
        this.estado = estado;
        this.pais = pais;
        this.telefone = telefone;
        this.cep = cep;
        this.pedido = gerarPedido.apply(this);
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getNome() {
        return nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public String getDocumento() {
        return documento;
    }

    public String getEndereco() {
        return endereco;
    }

    public String getComplemento() {
        return complemento;
    }

    public String getCidade() {
        return cidade;
    }

    public Estado getEstado() {
        return estado;
    }

    public Pais getPais() {
        return pais;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getCep() {
        return cep;
    }

    public Pedido getPedido() {
        return pedido;
    }
}
