package com.virellarent.backend.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.virellarent.backend.entities.Tarifa;

public interface TarifaRepository extends JpaRepository <Tarifa, Long>{
    List<Tarifa> findByEspacioId(Long idEspacio);
}
