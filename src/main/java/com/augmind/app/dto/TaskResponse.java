package com.augmind.app.dto;

import com.augmind.app.domain.Priority;

import java.time.LocalDateTime;

public record TaskResponse(
    Long id,
    String text,
    Priority priority,
    boolean completed,
    LocalDateTime createdAt
) {
}
