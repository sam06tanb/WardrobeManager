package dev.samir.backangulart.api.dto.mapper;

import dev.samir.backangulart.api.dto.ClothResponseDto;
import dev.samir.backangulart.api.dto.CreateClothRequestDto;
import dev.samir.backangulart.domain.model.Cloth;
import org.springframework.stereotype.Component;

@Component
public class ClothDtoMapper {


    public Cloth toDomain(CreateClothRequestDto dto) {
        return new Cloth(dto.id(), dto.name(), dto.size(), dto.color());
    }

    public ClothResponseDto toResponseDto(Cloth cloth) {
        return new ClothResponseDto(cloth.getId(),cloth.getName(), cloth.getSize(), cloth.getColor());
    }

}
