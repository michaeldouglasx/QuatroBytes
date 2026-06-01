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

    @Column(nullable = true, name = "descricao",length = 255)
    private String descricao;

    @Column(nullable = false, name = "preco")
    private BigDecimal preco;

    Integer quantidadeEstoque;
    private LocalDateTime dataRegistro;
}
