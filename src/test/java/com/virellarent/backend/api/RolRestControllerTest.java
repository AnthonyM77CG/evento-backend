package com.virellarent.backend.api;

import com.virellarent.backend.entities.Rol;
import com.virellarent.backend.services.RolService;
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

public class RolRestControllerTest {
    @Mock
    private RolService rolService;
    @InjectMocks
    private RolRestController rolRestController;
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(rolRestController).build();
    }

    @Test
    void testCreateRol() throws Exception {
        Rol rol = new Rol();
        rol.setId(1L);
        rol.setNombre("ADMIN");
        when(rolService.createRol(any(Rol.class))).thenReturn(rol);
        mockMvc.perform(post("/api/roles/agregar")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"nombre\":\"ADMIN\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.nombre").value("ADMIN"));
    }

    @Test
    void testGetRolById() throws Exception {
        Rol rol = new Rol();
        rol.setId(1L);
        rol.setNombre("ADMIN");
        when(rolService.getRolById(1L)).thenReturn(Optional.of(rol));
        mockMvc.perform(get("/api/roles/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.nombre").value("ADMIN"));
    }

    @Test
    void testGetAllRoles() throws Exception {
        when(rolService.getAllRoles()).thenReturn(Collections.emptyList());
        mockMvc.perform(get("/api/roles"))
                .andExpect(status().isOk());
    }

    @Test
    void testUpdateRol() throws Exception {
        Rol rol = new Rol();
        rol.setId(1L);
        when(rolService.updateRol(any(Long.class), any(Rol.class))).thenReturn(rol);
        mockMvc.perform(put("/api/roles/actualizar/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"nombre\":\"USER\"}"))
                .andExpect(status().isOk());
    }

    @Test
    void testDeleteRol() throws Exception {
        mockMvc.perform(delete("/api/roles/eliminar/1"))
                .andExpect(status().isNoContent());
    }
}
