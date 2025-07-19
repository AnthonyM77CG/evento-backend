package com.virellarent.backend.services;

import com.virellarent.backend.entities.Pago;
import com.virellarent.backend.entities.Reserva;
import com.virellarent.backend.repositories.ReservaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReservaService {

    private final ReservaRepository reservaRepository;
    private final PagoService pagoService;

    @Transactional
    public Reserva createReservaYPago(Reserva reserva, Pago pago) {
        // Crear la reserva
        Reserva nuevaReserva = reservaRepository.save(reserva);
        
        // Asociar la reserva al pago
        pago.setReserva(nuevaReserva);
        
        // Crear el pago
        pagoService.createPago(pago);

        return nuevaReserva;
    }

    public Reserva createReserva(Reserva reserva) {
        return reservaRepository.save(reserva);
    }

    public List<Reserva> getReservasByUsuarioId(Long idUsuario) {
        return reservaRepository.findByUsuarioId(idUsuario);
    }

    public List<Reserva> getReservasByEspacioEventoId(Long idEspacioEvento) {
        return reservaRepository.findByEspacioEventoId(idEspacioEvento);
    }

    public List<Reserva> getReservasByPlanId(Long idPlan) {
        return reservaRepository.findByPlanId(idPlan);
    }

    public Optional<Reserva> getReservaById(Long id) {
        return reservaRepository.findById(id);
    }

    public List<Reserva> getAllReservas() {
        return reservaRepository.findAll();
    }

    public Reserva updateReserva(Long id, Reserva reservaDetails) {
        Reserva reserva = reservaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reserva no encontrada"));

        reserva.setFecha(reservaDetails.getFecha());
        reserva.setHoraInicio(reservaDetails.getHoraInicio());
        reserva.setHoraFin(reservaDetails.getHoraFin());
        reserva.setEstado(reservaDetails.getEstado());
        return reservaRepository.save(reserva);
    }

    public void deleteReserva(Long id) {
        Reserva reserva = reservaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reserva no encontrada"));
        reservaRepository.delete(reserva);
    }

    @Transactional
    public void eliminarReservaYPago(Long reservaId) {
        try {
            Reserva reserva = reservaRepository.findById(reservaId)
                    .orElseThrow(() -> new RuntimeException("Reserva no encontrada"));

            reservaRepository.delete(reserva);

        } catch (Exception e) {
            throw new RuntimeException("Error al eliminar reserva y pago: " + e.getMessage());
        }
    }
}
