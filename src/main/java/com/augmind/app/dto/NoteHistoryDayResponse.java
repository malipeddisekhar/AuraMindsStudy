package com.augmind.app.dto;

import java.time.LocalDate;

public record NoteHistoryDayResponse(
    LocalDate date,
    long total
) {
}
