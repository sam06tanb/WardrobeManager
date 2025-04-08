package dev.samir.backangulart.Crud.DtoMapper;

import dev.samir.backangulart.Crud.EnumCloth;

public record ClothDto(
        String name,
        EnumCloth size,
        String color
) {}
