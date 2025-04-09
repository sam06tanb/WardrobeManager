package dev.samir.backangulart.infrastructure.Repository;

import dev.samir.backangulart.domain.EnumCloth;
import dev.samir.backangulart.infrastructure.entity.ClothEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface SpringDataClothRepository extends JpaRepository<ClothEntity, Long> {

    List<ClothEntity> findBySize(EnumCloth size);

}
