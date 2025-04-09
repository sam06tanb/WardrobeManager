package dev.samir.backangulart.infrastructure.Repository;

import dev.samir.backangulart.infrastructure.entity.ClothEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpringDataClothRepository extends JpaRepository<ClothEntity, Long> {
}
