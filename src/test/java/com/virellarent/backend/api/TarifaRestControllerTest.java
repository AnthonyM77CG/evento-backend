package com.virellarent.backend.api;

import com.virellarent.backend.entities.Tarifa;
import com.virellarent.backend.services.TarifaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.Optional;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class TarifaRestControllerTest {
    @Mock
    private TarifaService tarifaService;
    @InjectMocks
    private TarifaRestController tarifaRestController;
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(tarifaRestController).build();
    }

    @Test
    void testCreateTarifa() throws Exception {
        Tarifa tarifa = new Tarifa();
        tarifa.setId(1L);
        when(tarifaService.createTarifa(any(Tarifa.class))).thenReturn(tarifa);
        mockMvc.perform(post("/api/tarifas/agregar")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"precio\":100.00}"))
                .andExpect(status().isCreated());
    }

        @Test
    void testGetTarifaById() throws Exception {
        Tarifa tarifa = new Tarifa();
        tarifa.setId(1L);
        tarifa.setPrecio(BigDecimal.valueOf(100.00));
        when(tarifaService.getTarifaById(1L)).thenReturn(Optional.of(tarifa));
        mockMvc.perform(get("/api/tarifas/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.precio").value(100.00));
    }
    @Test
    void testGetAllTarifas() throws Exception {
        when(tarifaService.getAllTarifas()).thenReturn(Collections.emptyList());
        mockMvc.perform(get("/api/tarifas"))
                .andExpect(status().isOk());
    }

    @Test
    void testUpdateTarifa() throws Exception {
        Tarifa tarifa = new Tarifa();
        tarifa.setId(1L);
        when(tarifaService.updateTarifa(any(Long.class), any(Tarifa.class))).thenReturn(tarifa);
        mockMvc.perform(put("/api/tarifas/actualizar/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"precio\":150.00}"))
                .andExpect(status().isOk());
    }

    @Test
    void testDeleteTarifa() throws Exception {
        mockMvc.perform(delete("/api/tarifas/eliminar/1"))
                .andExpect(status().isNoContent());
    }
}
