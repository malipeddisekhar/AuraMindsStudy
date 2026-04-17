package com.augmind.app.dto;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record ScheduleCreateRequest(
    LocalDate date,
    @NotNull LocalTime time,
    @NotBlank @Size(max = 120) String title,
    @NotBlank @Size(max = 240) String description,
    @NotBlank @Pattern(regexp = "^#([A-Fa-f0-9]{6})$") String color
) {
}
