package com.virellarent.backend.api;

import com.virellarent.backend.entities.Plan;
import com.virellarent.backend.services.PlanService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import java.util.Collections;
import java.util.Optional;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class PlanRestControllerTest {
    @Mock
    private PlanService planService;

    @InjectMocks
    private PlanRestController planRestController;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(planRestController).build();
    }

    @Test
    void testCreatePlan() throws Exception {
        Plan plan = new Plan();
        plan.setId(1L);
        when(planService.createPlan(any(Plan.class))).thenReturn(plan);
        mockMvc.perform(post("/api/planes/agregar")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"nombre\":\"Plan Test\"}"))
                .andExpect(status().isCreated());
    }

    @Test
    void testGetPlanById() throws Exception {
        Plan plan = new Plan();
        plan.setId(1L);
        when(planService.getPlanById(1L)).thenReturn(Optional.of(plan));
        mockMvc.perform(get("/api/planes/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));
    }

    @Test
    void testGetAllPlanes() throws Exception {
        when(planService.getAllPlanes()).thenReturn(Collections.emptyList());
        mockMvc.perform(get("/api/planes"))
                .andExpect(status().isOk());
    }

    @Test
    void testUpdatePlan() throws Exception {
        Plan plan = new Plan();
        plan.setId(1L);
        when(planService.updatePlan(any(Long.class), any(Plan.class))).thenReturn(plan);
        mockMvc.perform(put("/api/planes/actualizar/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"nombre\":\"Plan Actualizado\"}"))
                .andExpect(status().isOk());
    }

    @Test
    void testDeletePlan() throws Exception {
        mockMvc.perform(delete("/api/planes/eliminar/1"))
                .andExpect(status().isNoContent());
    }
}
