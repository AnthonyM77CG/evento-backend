package com.virellarent.backend.api;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Collections;
import java.util.Optional;

import com.virellarent.backend.entities.Usuario;
import com.virellarent.backend.services.UsuarioService;

public class UsuarioRestControllerTest {
    @Mock
    private UsuarioService usuarioService;

    @InjectMocks
    private UsuarioRestController usuarioRestController;
    
    private MockMvc mockMvc;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(usuarioRestController).build();
    }

    @Test
    void testCreateUsuario() throws Exception {
        Usuario usuario = new Usuario();
        usuario.setId(1L);
        when(usuarioService.createUsuario(any(Usuario.class))).thenReturn(usuario);
        mockMvc.perform(post("/api/usuarios/agregar")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"usuario\":\"test\",\"correo\":\"test@test.com\"}"))
                .andExpect(status().isCreated());
    }
    
    @Test
    void testGetUsuarioById() throws Exception {
        Usuario usuario = new Usuario();
        usuario.setId(1L);
        when(usuarioService.getUsuarioById(1L)).thenReturn(Optional.of(usuario));
        mockMvc.perform(get("/api/usuarios/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));
    }

    @Test
    void testGetAllUsuarios() throws Exception {
        when(usuarioService.getAllUsuarios()).thenReturn(Collections.emptyList());
        mockMvc.perform(get("/api/usuarios"))
                .andExpect(status().isOk());
    }

    @Test
    void testUpdateUsuario() throws Exception {
        Usuario usuario = new Usuario();
        usuario.setId(1L);
        when(usuarioService.updateUsuario(any(Long.class), any(Usuario.class))).thenReturn(usuario);
        mockMvc.perform(put("/api/usuarios/actualizar/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"usuario\":\"updated\"}"))
                .andExpect(status().isOk());
    }

    @Test
    void testDeleteUsuario() throws Exception {
        mockMvc.perform(delete("/api/usuarios/eliminar/1"))
                .andExpect(status().isNoContent());
    }
}
