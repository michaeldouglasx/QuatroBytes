package com.example.QuatroBytes.service;

import com.example.QuatroBytes.dto.auth.RegisterDTO;
import com.example.QuatroBytes.dto.senha.AlterarSenhaDTO;
import com.example.QuatroBytes.dto.usuario.UsuarioRequestDTO;
import com.example.QuatroBytes.dto.usuario.UsuarioResponseDTO;
import com.example.QuatroBytes.model.Perfil;
import com.example.QuatroBytes.model.Usuario;
import com.example.QuatroBytes.repository.UsuarioRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;
    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return usuarioRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado!"));
    }
    public void cadastrarUsuario(RegisterDTO data){
        if (usuarioRepository.findByUsername(data.username()).isPresent()) {
            throw new IllegalArgumentException("Username já está em uso!");
        }
        String passwordHash = new BCryptPasswordEncoder().encode(data.senha());
        Usuario user = new Usuario(data.username(), passwordHash, data.perfil());
        usuarioRepository.save(user);
    }

    public void desativarUsuario(long id){
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("Usuário não encontrado!"));

        String usernameLogado = org.springframework.security.core.context.SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();

        if(usuario.getPerfil() == Perfil.ADMIN && usuario.getUsername().equals(usernameLogado)){
            throw new IllegalArgumentException("Não é permitido desativar a própria conta de Admin");
        }

        usuario.setAtivo(false);
        usuarioRepository.save(usuario);


    }

    public void alterarSenha(long id, AlterarSenhaDTO senhaDTO){

        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("Usuário não encontrado!"));
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        if(!encoder.matches(senhaDTO.senhaAtual(), usuario.getSenhaHash())){
            throw new IllegalArgumentException("A senha atual está incorreta!");
        }
        String novaSenhaCriptografada = encoder.encode(senhaDTO.novaSenha());
        usuario.setSenhaHash(novaSenhaCriptografada);
        usuarioRepository.save(usuario);
    }


    public UsuarioResponseDTO ativarUsuario(Long id){
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("Usuário não encontrado!"));
        usuario.setAtivo(true);
        usuarioRepository.save(usuario);
        return new UsuarioResponseDTO(usuario.getUsername(), usuario.getAtivo(), usuario.getPerfil());
    }


}
