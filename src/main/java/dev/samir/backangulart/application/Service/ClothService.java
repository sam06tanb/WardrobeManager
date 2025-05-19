package dev.samir.backangulart.application.Service;

import dev.samir.backangulart.api.exception.ResourceNotFoundException;
import dev.samir.backangulart.domain.EnumCloth;
import dev.samir.backangulart.domain.model.Cloth;
import dev.samir.backangulart.domain.ports.ClothRepositoryPort;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ClothService {

    private final ClothRepositoryPort repository;

    public ClothService(ClothRepositoryPort repository) {
        this.repository = repository;
    }

    public List<Cloth> listAll() {
        return repository.findAll();
    }

    public Cloth findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cloth not found"));
    }

    public Cloth create(Cloth cloth) {
        return repository.save(cloth);
    }

    public Optional<Cloth> update(Long id, Cloth updatedData) {
        return repository.findById(id).flatMap(existing -> {
            existing.setName(updatedData.getName());
            existing.setColor(updatedData.getColor());
            existing.setSize(updatedData.getSize());
            return Optional.of(repository.save(existing));
        });
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public List<Cloth> findBySize(EnumCloth size) {
        return repository.findBySize(size);
    }

}
