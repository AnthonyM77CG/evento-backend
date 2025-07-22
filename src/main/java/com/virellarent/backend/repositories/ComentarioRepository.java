package com.virellarent.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.virellarent.backend.entities.Comentario;

public interface ComentarioRepository extends JpaRepository<Comentario, Long> {
}
