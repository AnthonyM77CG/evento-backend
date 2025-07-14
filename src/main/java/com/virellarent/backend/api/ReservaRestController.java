package com.virellarent.backend.api;

import java.util.List;
import java.util.Optional;

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

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/reservas")
@RequiredArgsConstructor
public class ReservaRestController {
    
    private final ReservaService reservaService;

    //Obtener las reservas de un usuario
    @GetMapping("/usuario/{idUsuario}")
    public ResponseEntity<List<Reserva>> getReservasByUsuario(@PathVariable Long idUsuario) {
        List<Reserva> reservas = reservaService.getReservasByUsuarioId(idUsuario);
        return ResponseEntity.ok(reservas);
    }

    //Obtener las reservas por espacio de evento
    @GetMapping("/espacio/{idEspacioEvento}")
    public ResponseEntity<List<Reserva>> getReservasByEspacioEvento(@PathVariable Long idEspacioEvento) {
        List<Reserva> reservas = reservaService.getReservasByEspacioEventoId(idEspacioEvento);
        return ResponseEntity.ok(reservas);
    }

    //Obtener las reservas por plan
    @GetMapping("/plan/{idPlan}")
    public ResponseEntity<List<Reserva>> getReservasByPlan(@PathVariable Long idPlan) {
        List<Reserva> reservas = reservaService.getReservasByPlanId(idPlan);
        return ResponseEntity.ok(reservas);
    }

    // Crear Reserva
    @PostMapping("/agregar")
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
    @PutMapping("/actualizar/{id}")
    public ResponseEntity<Reserva> updateReserva(@PathVariable Long id, @RequestBody Reserva reservaDetails) {
        Reserva updatedReserva = reservaService.updateReserva(id, reservaDetails);
        return ResponseEntity.ok(updatedReserva);
    }

    // Endpoint para eliminar Reserva
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Void> deleteReserva(@PathVariable Long id) {
        reservaService.deleteReserva(id);
        return ResponseEntity.noContent().build();
    }

     // Transaccional - Endpoint para eliminar reserva y su pago asociado
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
