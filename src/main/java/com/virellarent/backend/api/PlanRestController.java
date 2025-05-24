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

import com.virellarent.backend.entities.Plan;
import com.virellarent.backend.services.PlanService;

@RestController
@RequestMapping("/api/planes")
public class PlanRestController {

    @Autowired
    private PlanService planService;

    // Crear Plan
    @PostMapping
    public ResponseEntity<Plan> createPlan(@RequestBody Plan plan) {
        Plan newPlan = planService.createPlan(plan);
        return new ResponseEntity<>(newPlan, HttpStatus.CREATED);
    }

    // Obtener Plan por ID
    @GetMapping("/{id}")
    public ResponseEntity<Plan> getPlanById(@PathVariable Long id) {
        Optional<Plan> plan = planService.getPlanById(id);
        return plan.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Obtener todos los Planes
    @GetMapping
    public ResponseEntity<List<Plan>> getAllPlanes() {
        List<Plan> planes = planService.getAllPlanes();
        return ResponseEntity.ok(planes);
    }

    // Actualizar Plan
    @PutMapping("/{id}")
    public ResponseEntity<Plan> updatePlan(@PathVariable Long id, @RequestBody Plan planDetails) {
        Plan updatedPlan = planService.updatePlan(id, planDetails);
        return ResponseEntity.ok(updatedPlan);
    }

    // Eliminar Plan
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePlan(@PathVariable Long id) {
        planService.deletePlan(id);
        return ResponseEntity.noContent().build();
    }
}
