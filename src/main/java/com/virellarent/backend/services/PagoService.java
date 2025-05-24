package com.virellarent.backend.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.virellarent.backend.entities.Pago;
import com.virellarent.backend.repositories.PagoRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PagoService {

    private final PagoRepository pagoRepository;

    // Crear Pago
    public Pago createPago(Pago pago) {
        return pagoRepository.save(pago);
    }

    // Buscar Pago por ID
    public Optional<Pago> getPagoById(Long id) {
        return pagoRepository.findById(id);
    }

    // Obtener todos los Pagos
    public List<Pago> getAllPagos() {
        return pagoRepository.findAll();
    }

    // Actualizar Pago
    public Pago updatePago(Long id, Pago pagoDetails) {
        Pago pago = pagoRepository.findById(id).orElseThrow(() -> new RuntimeException("Pago no encontrado"));
        pago.setMonto(pagoDetails.getMonto());
        pago.setMetodoPago(pagoDetails.getMetodoPago());
        pago.setEstadoPago(pagoDetails.getEstadoPago());
        return pagoRepository.save(pago);
    }

    // Eliminar Pago
    public void deletePago(Long id) {
        Pago pago = pagoRepository.findById(id).orElseThrow(() -> new RuntimeException("Pago no encontrado"));
        pagoRepository.delete(pago);
    }
}
