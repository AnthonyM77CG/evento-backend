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

import com.virellarent.backend.entities.Tarifa;
import com.virellarent.backend.services.TarifaService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/tarifas")
@RequiredArgsConstructor
public class TarifaRestController {

    private final TarifaService tarifaService;

    // Crear Tarifa
    @PostMapping("/agregar")
    public ResponseEntity<Tarifa> createTarifa(@RequestBody Tarifa tarifa) {
        Tarifa newTarifa = tarifaService.createTarifa(tarifa);
        return new ResponseEntity<>(newTarifa, HttpStatus.CREATED);
    }

    // Obtener Tarifa por ID
    @GetMapping("/{id}")
    public ResponseEntity<Tarifa> getTarifaById(@PathVariable Long id) {
        Optional<Tarifa> tarifa = tarifaService.getTarifaById(id);
        return tarifa.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Obtener todas las Tarifas
    @GetMapping
    public ResponseEntity<List<Tarifa>> getAllTarifas() {
        List<Tarifa> tarifas = tarifaService.getAllTarifas();
        return ResponseEntity.ok(tarifas);
    }

    // Actualizar Tarifa
    @PutMapping("/actualizar/{id}")
    public ResponseEntity<Tarifa> updateTarifa(@PathVariable Long id, @RequestBody Tarifa tarifaDetails) {
        Tarifa updatedTarifa = tarifaService.updateTarifa(id, tarifaDetails);
        return ResponseEntity.ok(updatedTarifa);
    }

    // Eliminar Tarifa
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Void> deleteTarifa(@PathVariable Long id) {
        tarifaService.deleteTarifa(id);
        return ResponseEntity.noContent().build();
    }
}
