package dev.samir.backangulart.application;

import dev.samir.backangulart.api.exception.ResourceNotFoundException;
import dev.samir.backangulart.application.Service.ClothService;
import dev.samir.backangulart.domain.EnumCloth;
import dev.samir.backangulart.domain.model.Cloth;
import dev.samir.backangulart.domain.ports.ClothRepositoryPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ClothServiceTest {

    private ClothRepositoryPort repositoryPort;
    private ClothService clothService;

    @BeforeEach
    public void setup() {
        repositoryPort = Mockito.mock(ClothRepositoryPort.class);
        clothService = new ClothService(repositoryPort);
    }

    @Test
    public void shouldCreateCloth() {
        Cloth cloth = new Cloth(1L, "Shirt", EnumCloth.sizeM, "Blue");
        when(repositoryPort.save(cloth)).thenReturn(cloth);
        Cloth result = clothService.create(cloth);
        assertEquals(cloth, result);
        verify(repositoryPort).save(cloth);
    }

    @Test
    public void shouldReturnCloth() {
        Long id = 1L;
        Cloth cloth = new Cloth(1L, "Shirt", EnumCloth.sizeM, "Blue");

        when(repositoryPort.findById(id)).thenReturn(Optional.of(cloth));

        Cloth result = clothService.findById(id);

        assertEquals(cloth, result);
        verify(repositoryPort).findById(id);
    }

    @Test
    public void shouldThrowExceptionWhenClothNotFound() {
        Long id = 999L;

        when(repositoryPort.findById(id)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> {
            clothService.findById(id);
        });

        verify(repositoryPort).findById(id);
    }

    @Test
    public void shouldUpdateCloth() {
        Long id = 1L;
        Cloth existing = new Cloth(id, "Shirt", EnumCloth.sizeM, "Blue");
        Cloth updated = new Cloth(id, "Polo Shirt", EnumCloth.sizeM, "White");

        when(repositoryPort.findById(id)).thenReturn(Optional.of(existing));
        when(repositoryPort.update(eq(id), any(Cloth.class)))
                .thenReturn(Optional.of(updated));

        Optional<Cloth> result = clothService.update(id, updated);

        assertTrue(result.isPresent());
        assertEquals("Polo Shirt", result.get().getName());
        assertEquals("White", result.get().getColor());
        assertEquals(EnumCloth.sizeM, result.get().getSize());
    }


    @Test
    public void shouldReturnEmptyWhenUpdateCloth() {
        Long id = 99L;
        Cloth updated = new Cloth(id, "Skirt", EnumCloth.sizeM, "Black");

        when(repositoryPort.findById(id)).thenReturn(Optional.empty());
        Optional<Cloth> result = clothService.update(id, updated);

        assertEquals(Optional.empty(), result);
        verify(repositoryPort).findById(id);
        verify(repositoryPort, never()).save(any());
    }

    @Test
    public void shouldDeleteCloth() {
        Long id = 1L;
        clothService.delete(id);

        verify(repositoryPort).deleteById(id);
    }

    @Test
    public void shouldReturnClothesBySize() {

        EnumCloth size = EnumCloth.sizeG;
        List<Cloth> expected = List.of(
                new Cloth(1L, "Polo jeans", EnumCloth.sizeG, "Black"),
                new Cloth(1L, "Shirt", EnumCloth.sizeG, "White")
        );

        when(repositoryPort.findBySize(size)).thenReturn(expected);

        List<Cloth> result = clothService.findBySize(size);

        assertEquals(expected.size(), result.size());
        assertEquals("Polo jeans", result.get(0).getName());
        assertEquals("Shirt", result.get(1).getName());
        verify(repositoryPort).findBySize(size);
    }
}
