package dev.samir.backangulart.api.dto;

import dev.samir.backangulart.domain.EnumCloth;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ClothDto(


        Long id,

        @NotBlank(message = "Name cannot be empty")
        String name,

        @NotNull(message = "Size must be provided")
        EnumCloth size,

        @NotBlank(message = "Color cannot be empty")
        String color

) {}