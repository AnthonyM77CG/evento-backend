package com.virellarent.backend.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.virellarent.backend.entities.Reserva;

public interface ReservaRepository extends JpaRepository <Reserva, Long>{
    List<Reserva> findByUsuarioId(Long idUsuario);
}
