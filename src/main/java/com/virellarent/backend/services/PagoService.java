package com.virellarent.backend.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.virellarent.backend.entities.Pago;
import com.virellarent.backend.entities.Reserva;
import com.virellarent.backend.repositories.PagoRepository;
import com.virellarent.backend.repositories.ReservaRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PagoService {

    private final PagoRepository pagoRepository;
    private final ReservaRepository reservaRepository; // Agregado para buscar la reserva

    public Pago createPago(Pago pago) {
        // Buscar la reserva desde su ID
        Reserva reserva = reservaRepository.findById(pago.getReserva().getId())
                .orElseThrow(() -> new RuntimeException("Reserva no encontrada"));
        
        // Asociar la reserva al pago
        pago.setReserva(reserva);

        // Guardar el pago en la base de datos
        return pagoRepository.save(pago);
    }

    public Pago getPagoConReserva(Long idPago) {
        return pagoRepository.findById(idPago)
                .orElseThrow(() -> new RuntimeException("Pago no encontrado"));
    }

    public Optional<Pago> getPagoById(Long id) {
        return pagoRepository.findById(id);
    }

    public List<Pago> getAllPagos() {
        return pagoRepository.findAll();
    }

    public Pago updatePago(Long id, Pago pagoDetails) {
        Pago pago = pagoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pago no encontrado"));
        pago.setMonto(pagoDetails.getMonto());
        pago.setMetodoPago(pagoDetails.getMetodoPago());
        pago.setEstadoPago(pagoDetails.getEstadoPago());
        return pagoRepository.save(pago);
    }

    public void deletePago(Long id) {
        Pago pago = pagoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pago no encontrado"));
        pagoRepository.delete(pago);
    }
}
