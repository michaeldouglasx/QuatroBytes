package com.example.QuatroBytes.controller;
import com.example.QuatroBytes.dto.auth.RegisterDTO;
import com.example.QuatroBytes.dto.usuario.UsuarioRequestDTO;
import com.example.QuatroBytes.dto.usuario.UsuarioResponseDTO;
import com.example.QuatroBytes.repository.UsuarioRepository;
import com.example.QuatroBytes.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/usuarios")
@Tag(name = "Usuários", description = "Gerenciamento de usuários")
public class UsuarioController {

    private final UsuarioService usuarioService;
    private final UsuarioRepository usuarioRepository;

    public UsuarioController(UsuarioService usuarioService, UsuarioRepository usuarioRepository) {
        this.usuarioService = usuarioService;
        this.usuarioRepository = usuarioRepository;
    }

    @Operation(summary = "Registrar usuário")
    @PostMapping
    public ResponseEntity<Void> register(@RequestBody @Valid RegisterDTO data){
        usuarioService.cadastrarUsuario(data);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Operation(summary = "Desativar Usuário")
    @PatchMapping("/{id}/desativar")
    public ResponseEntity<Void> desativarUsuário(@PathVariable("id") Long id){
        usuarioService.desativarUsuario(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Alterar senha")
    @PutMapping("/{id}/alterar-senha")
    public ResponseEntity<UsuarioResponseDTO> alterarSenha(@PathVariable("id") Long id, @RequestBody UsuarioRequestDTO usuarioRequestDTO){
        usuarioService.alterarSenha(id, usuarioRequestDTO);
        return ResponseEntity.noContent().build();
    }





}
