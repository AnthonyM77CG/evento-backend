package com.virellarent.backend.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.virellarent.backend.entities.Rol;
import com.virellarent.backend.repositories.RolRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RolService {
    private final RolRepository rolRepository;

    // Crear Rol
    public Rol createRol(Rol rol) {
        return rolRepository.save(rol);
    }

    // Buscar Rol por ID
    public Optional<Rol> getRolById(Long id) {
        return rolRepository.findById(id);
    }

    // Obtener todos los Roles
    public List<Rol> getAllRoles() {
        return rolRepository.findAll();
    }

    // Actualizar Rol
    public Rol updateRol(Long id, Rol rolDetails) {
        Rol rol = rolRepository.findById(id).orElseThrow(() -> new RuntimeException("Rol no encontrado"));
        rol.setNombre(rolDetails.getNombre());
        return rolRepository.save(rol);
    }

    // Eliminar Rol
    public void deleteRol(Long id) {
        Rol rol = rolRepository.findById(id).orElseThrow(() -> new RuntimeException("Rol no encontrado"));
        rolRepository.delete(rol);
    }
}
