package dev.samir.backangulart.api.dto;

import dev.samir.backangulart.domain.EnumCloth;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ClothDto(

        @NotBlank(message = "Name cannot be empty")
        String name,

        @NotBlank(message = "Color cannot be empty")
        EnumCloth size,

        @NotNull(message = "Size must be provided")
        String color

) {}