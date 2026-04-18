package com.augmind.app.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record ProfileNameRequest(
    @NotBlank @Size(max = 60) String name
) {
}
