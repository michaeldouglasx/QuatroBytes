package com.example.QuatroBytes.dto.senha;

import jakarta.validation.constraints.NotBlank;

public record AlterarSenhaDTO(
        @NotBlank(message = "A senha atual é obrigatória")
        String senhaAtual,
        @NotBlank(message = "A nova senha é obrigatória")
        String novaSenha) {
}
