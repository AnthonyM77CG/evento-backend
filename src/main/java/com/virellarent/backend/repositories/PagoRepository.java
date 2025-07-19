package com.virellarent.backend.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.virellarent.backend.entities.Pago;

public interface PagoRepository extends JpaRepository<Pago, Long> {
    List<Pago> findByReservaId(Long idReserva);
}
