package com.example.QuatroBytes.model;

import jakarta.persistence.*;
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

    @Column(nullable = true, name = "descricao", length = 255)
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
        this.preco = validarPreco(preco);
        this.quantidadeEstoque = validarQtdEstoque(quantidadeEstoque);
        this.estoqueMinimo = validarEstoqueMinimo(estoqueMinimo);
    }

    public void atualizarProduto(String nome, String descricao, BigDecimal preco, Integer quantidadeEstoque, Integer estoqueMinimo){
        this.nome = nome;
        this.descricao = descricao;
        this.preco = validarPreco(preco);
        this.quantidadeEstoque = validarQtdEstoque(quantidadeEstoque);
        this.estoqueMinimo = validarEstoqueMinimo(estoqueMinimo);

    }

    private Integer validarEstoqueMinimo(Integer estoqueMinimo){
        if (estoqueMinimo == null || estoqueMinimo<0 ){
            throw new IllegalArgumentException("Valor inválido! O estoque mínimo não pode ser nulo ou menor que zero.");
        }
        return estoqueMinimo;
    }

    private BigDecimal validarPreco(BigDecimal preco){
        if (preco == null || preco.compareTo(BigDecimal.ZERO) < 0 ){
            throw new IllegalArgumentException("Valor inválido! Preço não pode ser menor que 0!");
        }
        return preco;
    }
    private Integer validarQtdEstoque(Integer qtd){
        if (qtd == null || qtd < 0 ){
            throw new IllegalArgumentException("Valor inválido! Estoque não pode ser nulo ou menor que zero.");
        }
        return qtd;
    }

    @PrePersist
    public void prePersist(){
        this.dataRegistro = LocalDateTime.now();
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {this.id = id;}

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

    public BigDecimal getPreco() {return preco;}

    public void setPreco(BigDecimal preco) {
        this.preco = validarPreco(preco);
    }

    public Integer getEstoqueMinimo() {
        return estoqueMinimo;
    }

    public void setEstoqueMinimo(Integer estoqueMinimo) {
        this.estoqueMinimo = validarEstoqueMinimo(estoqueMinimo);
    }

    public Integer getQuantidadeEstoque() {
        return quantidadeEstoque;
    }

    public void setQuantidadeEstoque(Integer quantidadeEstoque) {
        this.quantidadeEstoque = validarQtdEstoque(quantidadeEstoque);
    }

    public LocalDateTime getDataRegistro() {
        return dataRegistro;
    }
}