package com.augmind.app.dto;

import java.time.LocalDate;
import java.time.LocalTime;

public record ScheduleResponse(
    Long id,
    LocalDate date,
    LocalTime time,
    String title,
    String description,
    String color
) {
}
