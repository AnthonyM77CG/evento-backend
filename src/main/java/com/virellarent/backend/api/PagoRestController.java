package com.virellarent.backend.api;

import com.virellarent.backend.entities.Pago;
import com.virellarent.backend.services.PagoService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;

import java.util.List;

@RestController
@RequestMapping("/api/pagos")
@RequiredArgsConstructor
public class PagoRestController {

    private final PagoService pagoService;

    @GetMapping("/reserva/{id}")
    public ResponseEntity<Pago> getPagoConReserva(@PathVariable Long id) {
        Pago pago = pagoService.getPagoConReserva(id);
        return ResponseEntity.ok(pago);
    }

    @PostMapping("/agregar")
    public ResponseEntity<Pago> createPago(@RequestBody Pago pago) {
        Pago newPago = pagoService.createPago(pago);
        return new ResponseEntity<>(newPago, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pago> getPagoById(@PathVariable Long id) {
        return pagoService.getPagoById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<Pago>> getAllPagos() {
        return ResponseEntity.ok(pagoService.getAllPagos());
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<Pago> updatePago(@PathVariable Long id, @RequestBody Pago pagoDetails) {
        Pago updatedPago = pagoService.updatePago(id, pagoDetails);
        return ResponseEntity.ok(updatedPago);
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Void> deletePago(@PathVariable Long id) {
        pagoService.deletePago(id);
        return ResponseEntity.noContent().build();
    }
}
