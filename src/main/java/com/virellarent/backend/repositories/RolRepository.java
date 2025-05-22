package com.virellarent.backend.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.virellarent.backend.entities.Rol;

public interface RolRepository extends JpaRepository<Rol, Long>{
    
    Optional<Rol> findByNombre(String nombre);

}
