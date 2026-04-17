package com.augmind.app.dto;

import java.time.LocalDateTime;

public record NoteResponse(
    Long id,
    String text,
    LocalDateTime createdAt
) {
}
