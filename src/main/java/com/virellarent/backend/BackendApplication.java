package com.virellarent.backend;

import java.time.LocalDateTime;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.virellarent.backend.entities.Rol;
import com.virellarent.backend.entities.Usuario;
import com.virellarent.backend.repositories.RolRepository;
import com.virellarent.backend.repositories.UsuarioRepository;

@SpringBootApplication
public class BackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(BackendApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(
            RolRepository rolRepository,
            UsuarioRepository userRepository,
            PasswordEncoder passwordEncoder) {
        return args -> {
            Rol rolAdmin = rolRepository.findByNombre("ADMIN")
                    .orElseGet(() -> rolRepository.save(Rol.builder().nombre("ADMIN").build()));

            boolean existsAdmin = userRepository.findAll()
                    .stream()
                    .anyMatch(u -> u.getCorreo().equalsIgnoreCase("adminis@gmail.com"));

            if (!existsAdmin) {
                var user = Usuario.builder()
                        .usuario("Administrador")
                        .correo("adminis@gmail.com")
                        .contraseña(passwordEncoder.encode("1234567"))
                        .rol(rolAdmin)
                        .creadoEn(LocalDateTime.now())
                        .build();
                userRepository.save(user);
            }
        };
    }
}
