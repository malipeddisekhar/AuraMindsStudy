package com.augmind.app.dto;

import java.time.LocalDate;

public record SubjectHistoryDayResponse(
    LocalDate date,
    long total
) {
}
