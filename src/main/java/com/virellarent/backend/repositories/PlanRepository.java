package com.virellarent.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.virellarent.backend.entities.Plan;

public interface PlanRepository extends JpaRepository <Plan, Long> {

}
