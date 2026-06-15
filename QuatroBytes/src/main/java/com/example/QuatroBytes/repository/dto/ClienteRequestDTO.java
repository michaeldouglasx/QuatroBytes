package com.example.QuatroBytes.repository.dto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ClienteRequestDTO(@NotBlank String nome, @NotNull String cpf, String telefone, String endereco) {


}
