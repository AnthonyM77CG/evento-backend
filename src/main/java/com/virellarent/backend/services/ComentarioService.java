package com.virellarent.backend.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.virellarent.backend.entities.Comentario;
import com.virellarent.backend.repositories.ComentarioRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ComentarioService {

    private final ComentarioRepository comentarioRepository;

    public List<Comentario> getAllComentarios() {
        return comentarioRepository.findAll();
    }

    public Comentario crearComentario(Comentario comentario) {
        return comentarioRepository.save(comentario);
    }

    public void deleteComentario(Long id) {
        Comentario comentario = comentarioRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Comentario no encontrado"));
        comentarioRepository.delete(comentario);
    }
}
