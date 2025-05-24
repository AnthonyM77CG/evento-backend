package com.virellarent.backend.api;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.virellarent.backend.entities.Pago;
import com.virellarent.backend.services.PagoService;

@RestController
@RequestMapping("/api/pagos")
public class PagoRestController {

    @Autowired
    private PagoService pagoService;

    // Crear Pago
    @PostMapping
    public ResponseEntity<Pago> createPago(@RequestBody Pago pago) {
        Pago newPago = pagoService.createPago(pago);
        return new ResponseEntity<>(newPago, HttpStatus.CREATED);
    }

    // Obtener Pago por ID
    @GetMapping("/{id}")
    public ResponseEntity<Pago> getPagoById(@PathVariable Long id) {
        Optional<Pago> pago = pagoService.getPagoById(id);
        return pago.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Obtener todos los Pagos
    @GetMapping
    public ResponseEntity<List<Pago>> getAllPagos() {
        List<Pago> pagos = pagoService.getAllPagos();
        return ResponseEntity.ok(pagos);
    }

    // Actualizar Pago
    @PutMapping("/{id}")
    public ResponseEntity<Pago> updatePago(@PathVariable Long id, @RequestBody Pago pagoDetails) {
        Pago updatedPago = pagoService.updatePago(id, pagoDetails);
        return ResponseEntity.ok(updatedPago);
    }

    // Eliminar Pago
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePago(@PathVariable Long id) {
        pagoService.deletePago(id);
        return ResponseEntity.noContent().build();
    }
}
