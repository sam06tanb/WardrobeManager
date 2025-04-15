package dev.samir.backangulart.application;

import dev.samir.backangulart.application.Service.ClothService;
import dev.samir.backangulart.domain.EnumCloth;
import dev.samir.backangulart.domain.model.Cloth;
import dev.samir.backangulart.domain.ports.ClothRepositoryPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
        Cloth cloth = new Cloth("Shirt", EnumCloth.sizeM, "Blue");
        when(repositoryPort.save(cloth)).thenReturn(cloth);
        Cloth result = clothService.create(cloth);
        assertEquals(cloth, result);
        verify(repositoryPort).save(cloth);
    }

    @Test
    public void shouldReturnCloth() {
        Long id = 1L;
        Cloth cloth = new Cloth("Shirt", EnumCloth.sizeM, "Blue");

        when(repositoryPort.findById(id)).thenReturn(Optional.of(cloth));
        Optional<Cloth> result = clothService.findById(id);

        assertEquals(Optional.of(cloth), result);
        verify(repositoryPort).findById(id);
    }

    @Test
    public void shouldReturnEmptyCloth() {
        Long id = 99L;

        when(repositoryPort.findById(id)).thenReturn(Optional.empty());
        Optional<Cloth> result = clothService.findById(id);

        assertEquals(Optional.empty(), result);
        verify(repositoryPort).findById(id);
    }

    @Test
    public void shouldUpdateCloth() {
        Long id = 1L;
        Cloth existing = new Cloth("Camiseta", EnumCloth.sizeM, "Azul");
        Cloth updated = new Cloth("Polo shirt", EnumCloth.sizeM, "White");

        when(repositoryPort.findById(id)).thenReturn(Optional.of(existing));
        when(repositoryPort.save(any(Cloth.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Optional<Cloth> result = clothService.update(id, updated);

        assertEquals("Polo shirt", result.get().getName());
        assertEquals(EnumCloth.sizeM, result.get().getSize());
        assertEquals("White", result.get().getColor());
        verify(repositoryPort).findById(id);
        verify(repositoryPort).save(existing);
    }

    @Test
    public void shouldReturnEmptyWhenUpdateCloth() {
        Long id = 99L;
        Cloth updated = new Cloth("Skirt", EnumCloth.sizeM, "Black");

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
                new Cloth("Polo jeans", EnumCloth.sizeG, "Black"),
                new Cloth("Shirt", EnumCloth.sizeG, "White")
        );

        when(repositoryPort.findBySize(size)).thenReturn(expected);

        List<Cloth> result = clothService.findBySize(size);

        assertEquals(expected.size(), result.size());
        assertEquals("Polo jeans", result.get(0).getName());
        assertEquals("Shirt", result.get(1).getName());
        verify(repositoryPort).findBySize(size);
    }
}
