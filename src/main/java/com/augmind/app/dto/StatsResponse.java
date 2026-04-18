package com.augmind.app.dto;

public record StatsResponse(
    long tasksDone,
    int sessionsCompleted,
    int studyMinutes,
    int studyHours,
    int streak
) {
}
