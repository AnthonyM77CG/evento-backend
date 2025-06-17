package com.virellarent.backend.services;

import com.virellarent.backend.models.EspacioEvento;
import com.virellarent.backend.repositories.EspacioEventoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class EspacioEventoServiceTest {

    @Mock
    private EspacioEventoRepository espacioEventoRepository;

    @InjectMocks
    private EspacioEventoService espacioEventoService;

    private EspacioEvento espacioEvento;

    @BeforeEach
    void setUp() {
        espacioEvento = new EspacioEvento();
        espacioEvento.setNombre("Espacio de prueba");
        espacioEvento.setUbicacion("Ubicación de prueba");
        espacioEvento.setDescripcion("Descripción de prueba");
        espacioEvento.setAforoMaximo(100);
    }

    @Test
    void testCreateEspacioEvento() {
        when(espacioEventoRepository.save(any(EspacioEvento.class))).thenReturn(espacioEvento);

        EspacioEvento result = espacioEventoService.createEspacioEvento(espacioEvento);

        System.out.println("Resultado creación: " + result);
        assertNotNull(result);
        assertEquals("Espacio de prueba", result.getNombre());
        verify(espacioEventoRepository, times(1)).save(any(EspacioEvento.class));
    }

    @Test
    void testGetEspacioEventoById() {
        when(espacioEventoRepository.findById(1L)).thenReturn(Optional.of(espacioEvento));

        Optional<EspacioEvento> result = espacioEventoService.getEspacioEventoById(1L);

        System.out.println("Resultado búsqueda por ID: " + result.orElse(null));
        assertTrue(result.isPresent());
        assertEquals("Espacio de prueba", result.get().getNombre());
    }

    @Test
    void testUpdateEspacioEvento() {
        EspacioEvento updatedEspacioEvento = new EspacioEvento();
        updatedEspacioEvento.setNombre("Nuevo Espacio");

        when(espacioEventoRepository.findById(1L)).thenReturn(Optional.of(espacioEvento));
        when(espacioEventoRepository.save(any(EspacioEvento.class))).thenReturn(updatedEspacioEvento);

        EspacioEvento result = espacioEventoService.updateEspacioEvento(1L, updatedEspacioEvento);

        System.out.println("Resultado actualización: " + result);
        assertNotNull(result);
        assertEquals("Nuevo Espacio", result.getNombre());
        verify(espacioEventoRepository, times(1)).save(any(EspacioEvento.class));
    }

    @Test
    void testDeleteEspacioEvento() {
        when(espacioEventoRepository.findById(1L)).thenReturn(Optional.of(espacioEvento));

        espacioEventoService.deleteEspacioEvento(1L);

        System.out.println("Eliminación ejecutada con éxito");
        verify(espacioEventoRepository, times(1)).delete(espacioEvento);
    }

    @Test
    void testDeleteEspacioEventoNotFound() {
        when(espacioEventoRepository.findById(1L)).thenReturn(Optional.empty());

        Exception exception = assertThrows(RuntimeException.class, () -> {
            espacioEventoService.deleteEspacioEvento(1L);
        });

        System.out.println("Mensaje de excepción: " + exception.getMessage());
        assertEquals("Espacio de evento no encontrado", exception.getMessage());
    }
}
