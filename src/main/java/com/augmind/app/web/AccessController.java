package com.augmind.app.web;

import java.time.Duration;
import java.time.Instant;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.augmind.app.dto.AccessCodeRequest;
import com.augmind.app.dto.AccessStatusResponse;
import com.augmind.app.service.AccessGateService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/access")
public class AccessController {

    public static final String SESSION_ACCESS_GRANTED = "ACCESS_GRANTED";
    public static final String SESSION_USER_NAME = "APP_USER_NAME";
    private static final String SESSION_FAILED_ATTEMPTS = "ACCESS_FAILED_ATTEMPTS";
    private static final String SESSION_LOCK_UNTIL_MS = "ACCESS_LOCK_UNTIL_MS";
    private static final int MAX_ATTEMPTS = 3;
    private static final Duration LOCK_DURATION = Duration.ofMinutes(15);

    private final AccessGateService accessGateService;

    public AccessController(AccessGateService accessGateService) {
        this.accessGateService = accessGateService;
    }

    @GetMapping("/status")
    public AccessStatusResponse status(HttpSession session) {
        boolean locked = isLocked(session);
        long remainingSeconds = locked ? getRemainingSeconds(session) : 0;
        return new AccessStatusResponse(isGranted(session), locked, remainingSeconds, getRemainingAttempts(session));
    }

    @PostMapping("/verify")
    public ResponseEntity<AccessStatusResponse> verify(@Valid @RequestBody AccessCodeRequest request, HttpSession session) {
        if (isLocked(session)) {
            return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS)
                .body(new AccessStatusResponse(false, true, getRemainingSeconds(session), 0));
        }

        if (!accessGateService.isValidCode(request.code())) {
            int failedAttempts = getFailedAttempts(session) + 1;
            session.setAttribute(SESSION_FAILED_ATTEMPTS, failedAttempts);

            if (failedAttempts >= MAX_ATTEMPTS) {
                long lockUntil = Instant.now().plus(LOCK_DURATION).toEpochMilli();
                session.setAttribute(SESSION_LOCK_UNTIL_MS, lockUntil);
                session.setAttribute(SESSION_FAILED_ATTEMPTS, 0);
                return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS)
                    .body(new AccessStatusResponse(false, true, getRemainingSeconds(session), 0));
            }

            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(new AccessStatusResponse(false, false, 0, MAX_ATTEMPTS - failedAttempts));
        }

        session.setAttribute(SESSION_ACCESS_GRANTED, Boolean.TRUE);
        session.setAttribute(SESSION_FAILED_ATTEMPTS, 0);
        session.removeAttribute(SESSION_LOCK_UNTIL_MS);
        return ResponseEntity.ok(new AccessStatusResponse(true, false, 0, MAX_ATTEMPTS));
    }

    @PostMapping("/logout")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void logout(HttpSession session) {
        session.removeAttribute(SESSION_ACCESS_GRANTED);
        session.removeAttribute(SESSION_FAILED_ATTEMPTS);
        session.removeAttribute(SESSION_LOCK_UNTIL_MS);
    }

    private boolean isGranted(HttpSession session) {
        Object val = session.getAttribute(SESSION_ACCESS_GRANTED);
        return val instanceof Boolean b && b;
    }

    private int getFailedAttempts(HttpSession session) {
        Object val = session.getAttribute(SESSION_FAILED_ATTEMPTS);
        if (val == null) {
            return 0;
        }
        try {
            return Integer.parseInt(val.toString());
        } catch (NumberFormatException ex) {
            return 0;
        }
    }

    private int getRemainingAttempts(HttpSession session) {
        int failed = getFailedAttempts(session);
        int remaining = MAX_ATTEMPTS - failed;
        return Math.max(0, remaining);
    }

    private boolean isLocked(HttpSession session) {
        Object val = session.getAttribute(SESSION_LOCK_UNTIL_MS);
        if (!(val instanceof Long lockUntilMs)) {
            return false;
        }

        long nowMs = Instant.now().toEpochMilli();
        if (lockUntilMs <= nowMs) {
            session.removeAttribute(SESSION_LOCK_UNTIL_MS);
            session.setAttribute(SESSION_FAILED_ATTEMPTS, 0);
            return false;
        }
        return true;
    }

    private long getRemainingSeconds(HttpSession session) {
        Object val = session.getAttribute(SESSION_LOCK_UNTIL_MS);
        if (!(val instanceof Long lockUntilMs)) {
            return 0;
        }
        long nowMs = Instant.now().toEpochMilli();
        long remainingMs = Math.max(0L, lockUntilMs - nowMs);
        return (remainingMs + 999) / 1000;
    }
}
