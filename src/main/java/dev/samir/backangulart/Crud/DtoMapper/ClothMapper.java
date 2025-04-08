package dev.samir.backangulart.Crud.DtoMapper;
import dev.samir.backangulart.Crud.Model.Cloth;

public class ClothMapper {

        public static ClothDto toDTO(Cloth cloth) {
            return new ClothDto(cloth.getName(), cloth.getSize(), cloth.getColor());
        }

        public static Cloth toEntity(ClothDto dto) {
            Cloth cloth = new Cloth();
            cloth.setName(dto.name());
            cloth.setSize(dto.size());
            cloth.setColor(dto.color());
            return cloth;
        }
    }



