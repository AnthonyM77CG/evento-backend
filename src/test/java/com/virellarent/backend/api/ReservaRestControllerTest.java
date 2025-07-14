package com.virellarent.backend.api;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.MediaType;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Collections;
import java.util.Optional;

import com.virellarent.backend.entities.Reserva;
import com.virellarent.backend.services.ReservaService;

public class ReservaRestControllerTest {
    @Mock
    private ReservaService reservaService;

    @InjectMocks
    private ReservaRestController reservaRestController;
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(reservaRestController).build();
    }

    @Test
    void testCreateReserva() throws Exception {
        Reserva reserva = new Reserva();
        reserva.setId(1L);
        when(reservaService.createReserva(any(Reserva.class))).thenReturn(reserva);
        mockMvc.perform(post("/api/reservas/agregar")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"fecha\":\"2023-01-01\"}"))
                .andExpect(status().isCreated());
    }

    @Test
    void testGetReservaById() throws Exception {
        Reserva reserva = new Reserva();
        reserva.setId(1L);
        when(reservaService.getReservaById(1L)).thenReturn(Optional.of(reserva));
        mockMvc.perform(get("/api/reservas/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));
    }

    @Test
    void testGetReservasByUsuario() throws Exception {
        when(reservaService.getReservasByUsuarioId(1L)).thenReturn(Collections.emptyList());
        mockMvc.perform(get("/api/reservas/usuario/1"))
                .andExpect(status().isOk());
    }

    @Test
    void testUpdateReserva() throws Exception {
        Reserva reservaActualizada = new Reserva();
        reservaActualizada.setId(1L);
        reservaActualizada.setAsistentes(4); // Campo numérico

        when(reservaService.updateReserva(eq(1L), any(Reserva.class)))
                .thenReturn(reservaActualizada);
        mockMvc.perform(put("/api/reservas/actualizar/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"cantidadPersonas\":4}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.asistentes").value(4));
    }

    @Test
    void testDeleteReservaYPago() throws Exception {
        mockMvc.perform(delete("/api/reservas/eliminar/con-pago/1"))
                .andExpect(status().isOk());
    }

    @Test
    void testDeleteReserva() throws Exception {
        mockMvc.perform(delete("/api/reservas/eliminar/1"))
                .andExpect(status().isNoContent());
    }
}
