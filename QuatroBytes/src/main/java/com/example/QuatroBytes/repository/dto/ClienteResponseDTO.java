package com.example.QuatroBytes.repository.dto;

import java.time.LocalDateTime;

public record ClienteResponseDTO( Long id, String nome, String cpf, String email, String telefone, LocalDateTime dataRegistro, String endereco) {

}
