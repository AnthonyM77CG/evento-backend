package com.virellarent.backend.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.virellarent.backend.entities.Plan;
import com.virellarent.backend.repositories.PlanRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PlanService {

    private final PlanRepository planRepository;

    public Plan createPlan(Plan plan) {
        return planRepository.save(plan);
    }

    public Optional<Plan> getPlanById(Long id) {
        return planRepository.findById(id);
    }

    public List<Plan> getAllPlanes() {
        return planRepository.findAll();
    }

    public Plan updatePlan(Long id, Plan planDetails) {
        Plan plan = planRepository.findById(id).orElseThrow(() -> new RuntimeException("Plan no encontrado"));
        plan.setNombre(planDetails.getNombre());
        plan.setDescripcion(planDetails.getDescripcion());
        plan.setPrecio(planDetails.getPrecio());
        return planRepository.save(plan);
    }

    public void deletePlan(Long id) {
        Plan plan = planRepository.findById(id).orElseThrow(() -> new RuntimeException("Plan no encontrado"));
        planRepository.delete(plan);
    }
}
