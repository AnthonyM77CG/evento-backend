package com.virellarent.backend.api;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.virellarent.backend.entities.Comentario;
import com.virellarent.backend.services.ComentarioService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/comentarios")
@RequiredArgsConstructor
public class ComentarioRestController {

    private final ComentarioService comentarioService;

    @GetMapping
    public ResponseEntity<List<Comentario>> getAllComentarios() {
        return ResponseEntity.ok(comentarioService.getAllComentarios());
    }

    @PostMapping("/crear")
    public ResponseEntity<Comentario> crearComentario(@RequestBody Comentario comentario) {
        Comentario nuevo = comentarioService.crearComentario(comentario);
        return ResponseEntity.ok(nuevo);
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Void> deleteComentario(@PathVariable Long id) {
        comentarioService.deleteComentario(id);
        return ResponseEntity.noContent().build();
    }
}
