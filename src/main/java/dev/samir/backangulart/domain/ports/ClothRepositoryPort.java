package dev.samir.backangulart.domain.ports;

import dev.samir.backangulart.domain.model.Cloth;
import java.util.List;
import java.util.Optional;

public interface ClothRepositoryPort {

    List<Cloth> findAll();

    Optional<Cloth> findById(Long id);

    Cloth save(Cloth cloth);

    void deleteById(Long id);

}
