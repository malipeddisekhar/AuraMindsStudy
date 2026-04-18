package com.augmind.app.web;

import java.util.Locale;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.augmind.app.dto.ProfileNameRequest;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/profile")
public class ProfileController {

    @GetMapping
    public Map<String, String> getProfile(HttpSession session) {
        ensureGranted(session);
        String userName = (String) session.getAttribute(AccessController.SESSION_USER_NAME);
        if (userName == null || userName.isBlank()) {
            userName = "Learner";
        }
        return Map.of("name", userName);
    }

    @PutMapping("/name")
    public Map<String, String> updateName(@Valid @RequestBody ProfileNameRequest request, HttpSession session) {
        ensureGranted(session);
        String normalized = normalizeName(request.name());
        session.setAttribute(AccessController.SESSION_USER_NAME, normalized);
        return Map.of("name", normalized);
    }

    private void ensureGranted(HttpSession session) {
        Object granted = session.getAttribute(AccessController.SESSION_ACCESS_GRANTED);
        if (!(granted instanceof Boolean b) || !b) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Access denied");
        }
    }

    private String normalizeName(String rawName) {
        String compact = rawName.trim().replaceAll("\\s+", " ");
        if (compact.isEmpty()) {
            return "Learner";
        }
        return compact.substring(0, 1).toUpperCase(Locale.ROOT) + compact.substring(1);
    }
}
