package com.augmind.app.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record SubjectCreateRequest(
    @NotBlank @Size(max = 100) String name,
    @NotBlank @Size(max = 8) String emoji,
    @Min(1) @Max(1000) int targetHours,
    @NotBlank @Pattern(regexp = "^#([A-Fa-f0-9]{6})$") String color
) {
}
