package com.example.QuatroBytes.dto.cliente;

import java.time.LocalDateTime;

public record ClienteResponseDTO( Long id, String nome, String cpf, String email, String telefone, LocalDateTime dataRegistro, String endereco) {

}
