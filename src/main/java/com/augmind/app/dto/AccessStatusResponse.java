package com.augmind.app.dto;

public record AccessStatusResponse(
    boolean granted,
    boolean locked,
    long remainingSeconds,
    int remainingAttempts
) {
}
