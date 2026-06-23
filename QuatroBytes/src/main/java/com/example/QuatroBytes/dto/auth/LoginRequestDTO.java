package com.example.QuatroBytes.dto.auth;

import jakarta.validation.constraints.NotBlank;

public record LoginRequestDTO(@NotBlank String username, @NotBlank String senha) {
}
