package com.example.QuatroBytes.dto.usuario;

import com.example.QuatroBytes.model.Perfil;

public record UsuarioResponseDTO (String username, Boolean ativo, Perfil perfil){
}
