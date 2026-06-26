package com.example.QuatroBytes.infra.seeder;

import com.example.QuatroBytes.model.Perfil;
import com.example.QuatroBytes.model.Usuario;
import com.example.QuatroBytes.repository.UsuarioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class AdminSeeder implements CommandLineRunner {
    private final UsuarioRepository usuarioRepository;

    public AdminSeeder(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public void run(String... args) throws Exception {


        if (usuarioRepository.findByUsername("admin").isEmpty()) {

            System.out.println("Nenhum Administrador encontrado no banco de dados.");
            System.out.println("Criando o Admin padrão.");


            String senhaCriptografada = new BCryptPasswordEncoder().encode("123456");


            Usuario admin = new Usuario("admin", senhaCriptografada, Perfil.ADMIN);


            usuarioRepository.save(admin);

            System.out.println("Admin padrão criado com sucesso!");
            System.out.println("Login: admin");
            System.out.println("Senha: 123456");
            System.out.println("=================================================================================");
            System.out.println("Crie um novo ADMIN e desative o ADMIN padrão.");
        }
    }


}
