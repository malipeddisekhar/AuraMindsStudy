package com.augmind.app.dto;

import java.time.LocalDate;

public record ScheduleHistoryDayResponse(
    LocalDate date,
    long total
) {
}
