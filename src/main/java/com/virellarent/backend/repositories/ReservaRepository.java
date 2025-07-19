package com.virellarent.backend.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.virellarent.backend.entities.Reserva;

public interface ReservaRepository extends JpaRepository<Reserva, Long> {

    @Query("SELECT r FROM Reserva r WHERE r.usuario.id = :idUsuario")
    List<Reserva> findByUsuarioId(@Param("idUsuario") Long idUsuario);

    @Query("SELECT r FROM Reserva r WHERE r.espacio.id = :idEspacioEvento")
    List<Reserva> findByEspacioEventoId(@Param("idEspacioEvento") Long idEspacioEvento);

    @Query("SELECT r FROM Reserva r WHERE r.plan.id = :idPlan")
    List<Reserva> findByPlanId(@Param("idPlan") Long idPlan);
}
