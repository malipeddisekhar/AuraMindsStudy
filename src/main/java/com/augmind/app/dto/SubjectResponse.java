package com.augmind.app.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record SubjectResponse(
    Long id,
    String name,
    String emoji,
    int targetHours,
    int loggedHours,
    String color,
    LocalDateTime createdAt,
    LocalDate subjectDate
) {
}
