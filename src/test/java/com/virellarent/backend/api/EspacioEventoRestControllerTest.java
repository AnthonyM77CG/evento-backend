package com.virellarent.backend.api;

import com.virellarent.backend.entities.EspacioEvento;
import com.virellarent.backend.services.EspacioEventoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class EspacioEventoRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private EspacioEventoService espacioEventoService;

    @InjectMocks
    private EspacioEventoRestController espacioEventoController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(espacioEventoController).build();
    }

    @Test
    void testCreateEspacioEvento() throws Exception {
        EspacioEvento espacio = new EspacioEvento();
        espacio.setId(1L);
        espacio.setNombre("Espacio de prueba");
        espacio.setUbicacion("Ubicación de prueba");
        espacio.setDescripcion("Descripción de prueba");
        espacio.setAforoMaximo(100);
        
        // Definir lo que devuelve el servicio
        when(espacioEventoService.createEspacioEvento(any(EspacioEvento.class))).thenReturn(espacio);

        // Realizar la solicitud POST y verificar la respuesta
        mockMvc.perform(post("/api/espacios_evento/agregar")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"nombre\":\"Espacio de prueba\",\"ubicacion\":\"Ubicación de prueba\",\"descripcion\":\"Descripción de prueba\",\"aforoMaximo\":100}")
        )
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.nombre").value("Espacio de prueba"))
                .andExpect(jsonPath("$.ubicacion").value("Ubicación de prueba"))
                .andExpect(jsonPath("$.descripcion").value("Descripción de prueba"))
                .andExpect(jsonPath("$.aforoMaximo").value(100));

        verify(espacioEventoService, times(1)).createEspacioEvento(any(EspacioEvento.class));
    }

    @Test
    void testGetEspacioEventoById() throws Exception {
        EspacioEvento espacio = new EspacioEvento();
        espacio.setId(1L);
        espacio.setNombre("Espacio de prueba");
        espacio.setUbicacion("Ubicación de prueba");
        espacio.setDescripcion("Descripción de prueba");
        espacio.setAforoMaximo(100);

        // Definir lo que devuelve el servicio
        when(espacioEventoService.getEspacioEventoById(1L)).thenReturn(java.util.Optional.of(espacio));

        // Realizar la solicitud GET y verificar la respuesta
        mockMvc.perform(get("/api/espacios_evento/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("Espacio de prueba"))
                .andExpect(jsonPath("$.ubicacion").value("Ubicación de prueba"))
                .andExpect(jsonPath("$.descripcion").value("Descripción de prueba"))
                .andExpect(jsonPath("$.aforoMaximo").value(100));

        verify(espacioEventoService, times(1)).getEspacioEventoById(1L);
    }

    @Test
    void testUpdateEspacioEvento() throws Exception {
        EspacioEvento espacio = new EspacioEvento();
        espacio.setId(1L);
        espacio.setNombre("Espacio actualizado");
        espacio.setUbicacion("Ubicación actualizada");
        espacio.setDescripcion("Descripción actualizada");
        espacio.setAforoMaximo(150);

        // Definir lo que devuelve el servicio
        when(espacioEventoService.updateEspacioEvento(eq(1L), any(EspacioEvento.class))).thenReturn(espacio);

        // Realizar la solicitud PUT y verificar la respuesta
        mockMvc.perform(put("/api/espacios_evento/actualizar/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"nombre\":\"Espacio actualizado\",\"ubicacion\":\"Ubicación actualizada\",\"descripcion\":\"Descripción actualizada\",\"aforoMaximo\":150}")
        )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("Espacio actualizado"))
                .andExpect(jsonPath("$.ubicacion").value("Ubicación actualizada"))
                .andExpect(jsonPath("$.descripcion").value("Descripción actualizada"))
                .andExpect(jsonPath("$.aforoMaximo").value(150));

        verify(espacioEventoService, times(1)).updateEspacioEvento(eq(1L), any(EspacioEvento.class));
    }

    @Test
    void testDeleteEspacioEvento() throws Exception {
        // Realizar la solicitud DELETE y verificar la respuesta
        mockMvc.perform(delete("/api/espacios_evento/eliminar/1"))
                .andExpect(status().isNoContent());

        verify(espacioEventoService, times(1)).deleteEspacioEvento(1L);
    }
}
