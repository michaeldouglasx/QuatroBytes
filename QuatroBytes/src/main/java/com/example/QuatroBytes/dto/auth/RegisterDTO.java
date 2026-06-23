package com.example.QuatroBytes.dto.auth;

import com.example.QuatroBytes.model.Perfil;

public record RegisterDTO(String username, String senha, Perfil perfil) {
}
