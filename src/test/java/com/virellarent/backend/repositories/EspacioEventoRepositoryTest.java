package com.virellarent.backend.repositories;

import com.virellarent.backend.entities.EspacioEvento;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class EspacioEventoRepositoryTest {

    @Autowired
    private EspacioEventoRepository espacioEventoRepository;

    @Test
    public void testSaveAndFind() {
        // Crear un objeto de prueba
        EspacioEvento espacioEvento = new EspacioEvento();
        espacioEvento.setNombre("Espacio de prueba");
        espacioEvento.setUbicacion("Ubicación de prueba");
        espacioEvento.setDescripcion("Descripción de prueba");
        espacioEvento.setAforoMaximo(100);

        // Guardar el objeto en la base de datos
        EspacioEvento savedEspacioEvento = espacioEventoRepository.save(espacioEvento);

        // Verificar que se ha guardado correctamente
        assertNotNull(savedEspacioEvento.getId());
        assertEquals("Espacio de prueba", savedEspacioEvento.getNombre());

        // Verificar que podemos encontrarlo por su ID
        EspacioEvento foundEspacioEvento = espacioEventoRepository.findById(savedEspacioEvento.getId()).orElse(null);
        assertNotNull(foundEspacioEvento);
        assertEquals("Espacio de prueba", foundEspacioEvento.getNombre());
    }
}
