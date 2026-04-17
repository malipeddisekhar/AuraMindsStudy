package com.augmind.app.dto;

import java.time.LocalDate;

public record TaskHistoryDayResponse(
    LocalDate date,
    long total,
    long completed
) {
}
