package com.virellarent.backend.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.virellarent.backend.entities.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
    
    Optional<Usuario> findByCorreo(String correo);
    
}
