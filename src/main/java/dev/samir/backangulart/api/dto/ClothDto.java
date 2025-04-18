package dev.samir.backangulart.api.dto;

import dev.samir.backangulart.domain.EnumCloth;

public record ClothDto(
        String name,
        EnumCloth size,
        String color
) {}