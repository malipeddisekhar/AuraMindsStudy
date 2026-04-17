package com.augmind.app.dto;

import jakarta.validation.constraints.NotBlank;

public record AccessCodeRequest(
    @NotBlank String code
) {
}
