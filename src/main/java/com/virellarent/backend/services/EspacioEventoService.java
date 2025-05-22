package com.virellarent.backend.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.virellarent.backend.entities.EspacioEvento;
import com.virellarent.backend.repositories.EspacioEventoRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EspacioEventoService {

    private final EspacioEventoRepository espacioEventoRepository;

    // Crear Espacio de Evento
    public EspacioEvento createEspacioEvento(EspacioEvento espacioEvento) {
        return espacioEventoRepository.save(espacioEvento);
    }

    // Buscar Espacio de Evento por ID
    public Optional<EspacioEvento> getEspacioEventoById(Long id) {
        return espacioEventoRepository.findById(id);
    }

    // Obtener todos los Espacios de Evento
    public List<EspacioEvento> getAllEspaciosEvento() {
        return espacioEventoRepository.findAll();
    }

    // Actualizar Espacio de Evento
    public EspacioEvento updateEspacioEvento(Long id, EspacioEvento espacioEventoDetails) {
        EspacioEvento espacioEvento = espacioEventoRepository.findById(id).orElseThrow(() -> new RuntimeException("Espacio de evento no encontrado"));
        espacioEvento.setNombre(espacioEventoDetails.getNombre());
        espacioEvento.setUbicacion(espacioEventoDetails.getUbicacion());
        espacioEvento.setDescripcion(espacioEventoDetails.getDescripcion());
        espacioEvento.setAforoMaximo(espacioEventoDetails.getAforoMaximo());
        return espacioEventoRepository.save(espacioEvento);
    }

    // Eliminar Espacio de Evento
    public void deleteEspacioEvento(Long id) {
        EspacioEvento espacioEvento = espacioEventoRepository.findById(id).orElseThrow(() -> new RuntimeException("Espacio de evento no encontrado"));
        espacioEventoRepository.delete(espacioEvento);
    }
}
