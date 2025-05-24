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

import com.virellarent.backend.entities.Reserva;
import com.virellarent.backend.services.ReservaService;

@RestController
@RequestMapping("/api/reservas")
public class ReservaRestController {
    @Autowired
    private ReservaService reservaService;

    // Crear Reserva
    @PostMapping
    public ResponseEntity<Reserva> createReserva(@RequestBody Reserva reserva) {
        Reserva newReserva = reservaService.createReserva(reserva);
        return new ResponseEntity<>(newReserva, HttpStatus.CREATED);
    }

    // Obtener Reserva por ID
    @GetMapping("/{id}")
    public ResponseEntity<Reserva> getReservaById(@PathVariable Long id) {
        Optional<Reserva> reserva = reservaService.getReservaById(id);
        return reserva.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Obtener todas las Reservas
    @GetMapping
    public ResponseEntity<List<Reserva>> getAllReservas() {
        List<Reserva> reservas = reservaService.getAllReservas();
        return ResponseEntity.ok(reservas);
    }

    // Actualizar Reserva
    @PutMapping("/{id}")
    public ResponseEntity<Reserva> updateReserva(@PathVariable Long id, @RequestBody Reserva reservaDetails) {
        Reserva updatedReserva = reservaService.updateReserva(id, reservaDetails);
        return ResponseEntity.ok(updatedReserva);
    }

    // Endpoint para eliminar Reserva
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReserva(@PathVariable Long id) {
        reservaService.deleteReserva(id);
        return ResponseEntity.noContent().build();
    }

    // Endpoint para eliminar reserva y su pago asociado
    @DeleteMapping("/completo/{id}") 
    public ResponseEntity<?> eliminarReservaYPago(@PathVariable Long id) {
        try {
            reservaService.eliminarReservaYPago(id);
            return ResponseEntity.ok().body("Reserva y pago eliminados exitosamente");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error interno del servidor");
        }
    }
}
