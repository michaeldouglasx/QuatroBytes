package com.example.QuatroBytes.repository.dto;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ClienteRequestDTO(@NotBlank String nome, @NotNull String cpf, @Email String email, String telefone, String endereco) {


}
