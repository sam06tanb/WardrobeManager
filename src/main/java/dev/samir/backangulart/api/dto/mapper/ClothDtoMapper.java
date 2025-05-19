package dev.samir.backangulart.api.dto.mapper;

import dev.samir.backangulart.api.dto.ClothDto;
import dev.samir.backangulart.domain.model.Cloth;
import org.springframework.stereotype.Component;

@Component
public class ClothDtoMapper {

    public ClothDto toDto(Cloth cloth) {
        return new ClothDto(cloth.getId(), cloth.getName(), cloth.getSize(), cloth.getColor());
    }

    public Cloth toDomain(ClothDto dto) {
        return new Cloth(dto.id(), dto.name(), dto.size(), dto.color());
    }

}
