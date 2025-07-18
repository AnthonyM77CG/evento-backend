package com.virellarent.backend.api;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.virellarent.backend.entities.Reserva;
import com.virellarent.backend.services.ReservaService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/reservas")
@RequiredArgsConstructor
public class ReservaRestController {

    private final ReservaService reservaService;

    @GetMapping("/usuario/{idUsuario}")
    public ResponseEntity<List<Reserva>> getReservasByUsuario(@PathVariable Long idUsuario) {
        List<Reserva> reservas = reservaService.getReservasByUsuarioId(idUsuario);
        return ResponseEntity.ok(reservas);
    }

    @GetMapping("/espacio/{idEspacioEvento}")
    public ResponseEntity<List<Reserva>> getReservasByEspacioEvento(@PathVariable Long idEspacioEvento) {
        List<Reserva> reservas = reservaService.getReservasByEspacioEventoId(idEspacioEvento);
        return ResponseEntity.ok(reservas);
    }

    @GetMapping("/plan/{idPlan}")
    public ResponseEntity<List<Reserva>> getReservasByPlan(@PathVariable Long idPlan) {
        List<Reserva> reservas = reservaService.getReservasByPlanId(idPlan);
        return ResponseEntity.ok(reservas);
    }

    @PostMapping("/agregar")
    public ResponseEntity<Reserva> createReserva(@RequestBody Reserva reserva) {
        Reserva newReserva = reservaService.createReserva(reserva);
        return new ResponseEntity<>(newReserva, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Reserva> getReservaById(@PathVariable Long id) {
        Optional<Reserva> reserva = reservaService.getReservaById(id);
        return reserva.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<Reserva>> getAllReservas() {
        List<Reserva> reservas = reservaService.getAllReservas();
        return ResponseEntity.ok(reservas);
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<Reserva> updateReserva(@PathVariable Long id, @RequestBody Reserva reservaDetails) {
        Reserva updatedReserva = reservaService.updateReserva(id, reservaDetails);
        return ResponseEntity.ok(updatedReserva);
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Void> deleteReserva(@PathVariable Long id) {
        reservaService.deleteReserva(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/eliminar/con-pago/{id}")
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
