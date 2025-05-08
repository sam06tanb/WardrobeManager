package dev.samir.backangulart.domain.ports;

import dev.samir.backangulart.domain.EnumCloth;
import dev.samir.backangulart.domain.model.Cloth;
import java.util.List;
import java.util.Optional;

public interface ClothRepositoryPort {

    List<Cloth> findAll();

    Optional<Cloth> findById(Long id);

    Cloth save(Cloth cloth);

    Optional<Cloth> update(Long id,Cloth cloth);

    void deleteById(Long id);

    List<Cloth> findBySize(EnumCloth size);
}
