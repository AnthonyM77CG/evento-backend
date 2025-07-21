package com.virellarent.backend.api;

import com.virellarent.backend.entities.Reserva;
import com.virellarent.backend.services.ReservaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/reservas")
@RequiredArgsConstructor
public class ReservaRestController {

    private final ReservaService reservaService;

    @GetMapping
    public ResponseEntity<List<Reserva>> getAllReservas() {
        List<Reserva> reservas = reservaService.getAllReservas();
        return ResponseEntity.ok(reservas);
    }

    @PostMapping("/agregar")
    public ResponseEntity<Reserva> createReserva(@RequestBody Reserva reserva) {
        try {
            Reserva nuevaReserva = reservaService.createReserva(reserva);
            return new ResponseEntity<>(nuevaReserva, HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

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

    @GetMapping("/{id}")
    public ResponseEntity<Reserva> getReservaById(@PathVariable Long id) {
        return reservaService.getReservaById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
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

}
