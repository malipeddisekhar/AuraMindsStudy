package com.augmind.app.dto;

import com.augmind.app.domain.Priority;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record TaskCreateRequest(
    @NotBlank @Size(max = 200) String text,
    @NotNull Priority priority
) {
}
