package com.virellarent.backend.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.virellarent.backend.entities.Pago;
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

    //JPQL - Obtener reservas por usuario
    public List<Reserva> getReservasByUsuarioId(Long idUsuario) {
        return reservaRepository.findByUsuarioId(idUsuario);
    }

    //JPQL - Obtener reservas por espacio de evento
    public List<Reserva> getReservasByEspacioEventoId(Long idEspacioEvento) {
        return reservaRepository.findByEspacioEventoId(idEspacioEvento);
    }

    //JPQL - Obtener reservas por plan
    public List<Reserva> getReservasByPlanId(Long idPlan) {
        return reservaRepository.findByPlanId(idPlan);
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
    public Reserva createReservaConPago(Reserva reserva, Pago pago) {
        // Guardar la reserva
        Reserva nuevaReserva = reservaRepository.save(reserva);
        
        // Asignar la reserva al pago
        pago.setReserva(nuevaReserva);
        
        // Guardar el pago
        pagoRepository.save(pago);
        
        return nuevaReserva;
    }
}
