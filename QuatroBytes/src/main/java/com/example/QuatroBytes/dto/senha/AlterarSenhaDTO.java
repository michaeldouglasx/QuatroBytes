package com.example.QuatroBytes.dto.senha;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record AlterarSenhaDTO(
        @NotBlank(message = "A senha atual é obrigatória")
        String senhaAtual,
        @Size(min = 6, message = "A senha deve ter no mínimo 6 caracteres")
        String novaSenha) {
}
