package com.virellarent.backend.api;

import com.virellarent.backend.entities.Pago;
import com.virellarent.backend.services.PagoService;
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
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class PagoRestControllerTest {
    @Mock
    private PagoService pagoService;
    @InjectMocks
    private PagoRestController pagoRestController;
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(pagoRestController).build();
    }

    @Test
    void testCreatePago() throws Exception {
        Pago pago = new Pago();
        pago.setId(1L);
        when(pagoService.createPago(any(Pago.class))).thenReturn(pago);
        mockMvc.perform(post("/api/pagos/agregar")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"monto\":100.00}"))
                .andExpect(status().isCreated());
    }

    @Test
    void testGetPagoById() throws Exception {
        Pago pago = new Pago();
        pago.setId(1L);
        pago.setMonto(BigDecimal.valueOf(100.00));
        when(pagoService.getPagoById(1L)).thenReturn(Optional.of(pago));
        mockMvc.perform(get("/api/pagos/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.monto").value(100.00));
    }

    @Test
    void testGetPagoConReserva() throws Exception {
        Pago pago = new Pago();
        pago.setId(1L);
        when(pagoService.getPagoConReserva(1L)).thenReturn(pago);
        mockMvc.perform(get("/api/pagos/reserva/1"))
                .andExpect(status().isOk());
    }

    @Test
    void testGetAllPagos() throws Exception {
        when(pagoService.getAllPagos()).thenReturn(Collections.emptyList());
        mockMvc.perform(get("/api/pagos"))
                .andExpect(status().isOk());
    }

    @Test
    void testUpdatePago() throws Exception {
        Pago pagoActualizado = new Pago();
        pagoActualizado.setId(1L);
        pagoActualizado.setMonto(BigDecimal.valueOf(150.00));
        when(pagoService.updatePago(eq(1L), any(Pago.class)))
                .thenReturn(pagoActualizado);
        mockMvc.perform(put("/api/pagos/actualizar/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"monto\":150.00}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.monto").value(150.00));
    }

    @Test
    void testDeletePago() throws Exception {
        mockMvc.perform(delete("/api/pagos/eliminar/1"))
                .andExpect(status().isNoContent());
    }
}
