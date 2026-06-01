package com.example.QuatroBytes.model;


import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name="cliente")
public class Cliente {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, name = "nome", length = 100)
    private String nome;

    @Column(unique = true, nullable = false)
    private String cpf;

    @Column(nullable = true, length = 30)
    private String telefone;

    @Column(nullable = false)
    private LocalDateTime dataRegistro;



}
