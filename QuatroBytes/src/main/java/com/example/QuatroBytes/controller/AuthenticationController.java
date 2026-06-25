package com.example.QuatroBytes.controller;


import com.example.QuatroBytes.dto.auth.LoginRequestDTO;
import com.example.QuatroBytes.dto.auth.RegisterDTO;

import com.example.QuatroBytes.model.Usuario;
import com.example.QuatroBytes.repository.UsuarioRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/auth")
@RestController
@Tag(name = "Autenticação", description = "Controle de Autenticação")
public class AuthenticationController {
    UsuarioRepository usuarioRepository;
    AuthenticationManager authenticationManager;

    public AuthenticationController(UsuarioRepository usuarioRepository, AuthenticationManager authenticationManager) {
        this.usuarioRepository = usuarioRepository;
        this.authenticationManager = authenticationManager;
    }


    @Operation(summary = "Autenticar usuário")
    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid LoginRequestDTO data){
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.username(), data.senha());
        var auth = this.authenticationManager.authenticate(usernamePassword);
        return ResponseEntity.ok().build();
    }


}
