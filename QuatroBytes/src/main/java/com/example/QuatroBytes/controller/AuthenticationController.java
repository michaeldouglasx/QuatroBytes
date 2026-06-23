package com.example.QuatroBytes.controller;


import com.example.QuatroBytes.dto.auth.LoginRequestDTO;
import com.example.QuatroBytes.dto.auth.RegisterDTO;
import com.example.QuatroBytes.infra.security.PasswordEncoderConfig;
import com.example.QuatroBytes.model.Usuario;
import com.example.QuatroBytes.repository.UsuarioRepository;
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
public class AuthenticationController {
    UsuarioRepository usuarioRepository;
    AuthenticationManager authenticationManager;

    public AuthenticationController(UsuarioRepository usuarioRepository, AuthenticationManager authenticationManager) {
        this.usuarioRepository = usuarioRepository;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid LoginRequestDTO data){
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.username(), data.senha());
        var auth = this.authenticationManager.authenticate(usernamePassword);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid RegisterDTO data){
        if (this.usuarioRepository.findByUsername(data.username())!= null){
            return ResponseEntity.badRequest().build();
        }

        String passwordHash = new BCryptPasswordEncoder().encode(data.senha());
        Usuario user = new Usuario(data.username(), passwordHash, data.perfil());
        usuarioRepository.save(user);
        return ResponseEntity.ok().build();

    }


}
