package dev.samir.backangulart.infrastructure.Repository;

import dev.samir.backangulart.domain.EnumCloth;
import dev.samir.backangulart.domain.model.Cloth;
import dev.samir.backangulart.domain.ports.ClothRepositoryPort;
import dev.samir.backangulart.infrastructure.entity.ClothEntity;
import dev.samir.backangulart.infrastructure.mapper.ClothEntityMapper;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class ClothJpaRepository implements ClothRepositoryPort {

   private final SpringDataClothRepository repository;
   private final ClothEntityMapper mapper;

   public ClothJpaRepository(SpringDataClothRepository repository, ClothEntityMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
   }

   @Override
   public List<Cloth> findAll() {
        return repository.findAll()
            .stream()
            .map(mapper::toDomain)
            .collect(Collectors.toList());
   }

   @Override
   public Optional<Cloth> findById(Long id) {
        return repository.findById(id)
            .map(mapper::toDomain);
   }

    @Override
    public Cloth save(Cloth cloth) {
        ClothEntity entity = mapper.toEntity(cloth);
        ClothEntity saved = repository.save(entity);
        return mapper.toDomain(saved);
    }

    @Override
    public Optional<Cloth> update(Long id, Cloth cloth) {
        return repository.findById(id).map(existing -> {
            existing.setName(cloth.getName());
            existing.setSize(cloth.getSize());
            existing.setColor(cloth.getColor());
            var updated = repository.save(existing);
            return mapper.toDomain(updated);
        });
    }

    @Override
    public void deleteById(Long id) {
            repository.deleteById(id);
        }

    @Override
    public List<Cloth> findBySize(EnumCloth size) {
        return repository.findBySize(size).stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }
}