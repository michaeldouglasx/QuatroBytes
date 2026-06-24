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

    @Column(unique = true, nullable = false, name = "cpf")
    private String cpf;

    @Column(unique = true, nullable = false, name = "email")
    private String email;

    @Column(nullable = true, length = 30, name= "telefone")
    private String telefone;

    @Column(name = "endereco", nullable = true, length = 100)
    private String endereco;

    @Column(nullable = false, name = "data_registro")
    private LocalDateTime dataRegistro;

    protected Cliente() {
    }

    public Cliente(String nome, String cpf, String email,  String telefone, String endereco) {
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.telefone = telefone;
        this.endereco = endereco;
    }

    public LocalDateTime getDataRegistro() {
        return dataRegistro;
    }

    public void setDataRegistro(LocalDateTime dataRegistro) {
        this.dataRegistro = dataRegistro;
    }

    public String getNome() {
        return nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @PrePersist
    public void prePersist(){
        this.dataRegistro = LocalDateTime.now();
    }

    public void atualizar(String nome, String cpf,String email, String telefone, String endereco){
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.telefone = telefone;
        this.endereco = endereco;
    }
}
