package com.example.QuatroBytes.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;


@Entity
@Table(name = "produto")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, name = "nome",length = 100)
    private String nome;

    @Column(nullable = true, name = "descricao",length = 255)
    private String descricao;

    @Column(nullable = false, name = "preco")
    private BigDecimal preco;

    @Column(nullable = false, name = "qtd_estoque")
    private Integer quantidadeEstoque;

    @Column(nullable = false, name = "estoque_minimo")
    private Integer estoqueMinimo;

    @Column(nullable = false, name = "data_registro")
    private LocalDateTime dataRegistro;


    protected Produto() {
    }

    public Produto(String nome, String descricao, BigDecimal preco, Integer quantidadeEstoque, Integer estoqueMinimo) {
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.quantidadeEstoque = quantidadeEstoque;
        this.estoqueMinimo = estoqueMinimo;
        this.dataRegistro = dataRegistro;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public Integer getEstoqueMinimo() {
        return estoqueMinimo;
    }

    public void setEstoqueMinimo(Integer estoqueMinimo) {
        this.estoqueMinimo = estoqueMinimo;
    }

    public Integer getQuantidadeEstoque() {
        return quantidadeEstoque;
    }

    public void setQuantidadeEstoque(Integer quantidadeEstoque) {
        this.quantidadeEstoque = quantidadeEstoque;
    }

    public LocalDateTime getDataRegistro() {
        return dataRegistro;
    }

    public void setDataRegistro(LocalDateTime dataRegistro) {
        this.dataRegistro = dataRegistro;
    }
    public void atualizarProduto(String nome, String descricao, BigDecimal preco, Integer quantidadeEstoque, Integer estoqueMinimo){
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.quantidadeEstoque = quantidadeEstoque;
        this.estoqueMinimo = estoqueMinimo;

    }

    @PrePersist
    public void prePersist(){
        this.dataRegistro = LocalDateTime.now();
    }

}
