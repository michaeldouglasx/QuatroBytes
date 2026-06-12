package com.example.QuatroBytes.repository.dto;

import java.time.LocalDateTime;

public record ClienteResponseDTO( String nome, String cpf, String telefone, LocalDateTime dataRegistro, String endereco) {

}
