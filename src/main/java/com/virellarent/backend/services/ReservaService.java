package com.virellarent.backend.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.virellarent.backend.entities.Reserva;
import com.virellarent.backend.repositories.PagoRepository;
import com.virellarent.backend.repositories.ReservaRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReservaService {
    
    private final ReservaRepository reservaRepository;
    private final PagoRepository pagoRepository;

    // Crear Reserva
    public Reserva createReserva(Reserva reserva) {
        return reservaRepository.save(reserva);
    }

    // Buscar Reserva por ID
    public Optional<Reserva> getReservaById(Long id) {
        return reservaRepository.findById(id);
    }

    // Obtener todas las Reservas
    public List<Reserva> getAllReservas() {
        return reservaRepository.findAll();
    }

    // Actualizar Reserva
    public Reserva updateReserva(Long id, Reserva reservaDetails) {
        Reserva reserva = reservaRepository.findById(id).orElseThrow(() -> new RuntimeException("Reserva no encontrada"));
        reserva.setFecha(reservaDetails.getFecha());
        reserva.setHoraInicio(reservaDetails.getHoraInicio());
        reserva.setHoraFin(reservaDetails.getHoraFin());
        reserva.setEstado(reservaDetails.getEstado());
        return reservaRepository.save(reserva);
    }

    // Eliminar Reserva
    public void deleteReserva(Long id) {
        Reserva reserva = reservaRepository.findById(id).orElseThrow(() -> new RuntimeException("Reserva no encontrada"));
        reservaRepository.delete(reserva);
    }

    //Transaccion
    @Transactional
    public void eliminarReservaYPago(Long reservaId) {
        try {
            Reserva reserva = reservaRepository.findById(reservaId)
                    .orElseThrow(() -> new RuntimeException("Reserva no encontrada"));

            pagoRepository.delete(reserva.getPago());

            reservaRepository.delete(reserva);

        } catch (Exception e) {
            throw new RuntimeException("Error al eliminar reserva y pago: " + e.getMessage());
        }
    }
}
