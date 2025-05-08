package dev.samir.backangulart.infrastructure.mapper;

import dev.samir.backangulart.domain.model.Cloth;
import dev.samir.backangulart.infrastructure.entity.ClothEntity;
import org.springframework.stereotype.Component;

@Component
public class ClothEntityMapper {

        public Cloth toDomain(ClothEntity clothEntity) {
            if (clothEntity == null) return null;
            return new Cloth(
                    clothEntity.getName(),
                    clothEntity.getSize(),
                    clothEntity.getColor());
        }

        public ClothEntity toEntity(Cloth domain) {
            if (domain == null) return null;
            ClothEntity clothEntity = new ClothEntity();
            clothEntity.setId(domain.getId());
            clothEntity.setName(domain.getName());
            clothEntity.setSize(domain.getSize());
            clothEntity.setColor(domain.getColor());
            return clothEntity;
        }
    }



