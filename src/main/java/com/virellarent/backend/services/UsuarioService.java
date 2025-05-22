package com.virellarent.backend.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.virellarent.backend.entities.Usuario;
import com.virellarent.backend.repositories.UsuarioRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;

    // Crear Usuario
    public Usuario createUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    // Buscar Usuario por ID
    public Optional<Usuario> getUsuarioById(Long id) {
        return usuarioRepository.findById(id);
    }

    // Obtener todos los Usuarios
    public List<Usuario> getAllUsuarios() {
        return usuarioRepository.findAll();
    }

    // Actualizar Usuario
    public Usuario updateUsuario(Long id, Usuario usuarioDetails) {
        Usuario usuario = usuarioRepository.findById(id).orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        usuario.setUsuario(usuarioDetails.getUsuario());
        usuario.setCorreo(usuarioDetails.getCorreo());
        usuario.setContraseña(usuarioDetails.getContraseña());
        return usuarioRepository.save(usuario);
    }

    // Eliminar Usuario
    public void deleteUsuario(Long id) {
        Usuario usuario = usuarioRepository.findById(id).orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        usuarioRepository.delete(usuario);
    }
}
