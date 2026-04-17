package com.augmind.app.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record NoteCreateRequest(
    @NotBlank @Size(max = 2000) String text
) {
}
