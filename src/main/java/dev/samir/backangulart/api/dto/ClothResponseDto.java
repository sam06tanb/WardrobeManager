package dev.samir.backangulart.api.dto;

import dev.samir.backangulart.domain.EnumCloth;

public record ClothResponseDto(
        Long id,
        String name,
        EnumCloth size,
        String color
) {}
