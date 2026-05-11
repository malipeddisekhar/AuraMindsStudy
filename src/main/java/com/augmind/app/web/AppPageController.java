package com.augmind.app.web;

import java.util.Locale;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.servlet.http.HttpSession;

@Controller
public class AppPageController {

    @GetMapping("/")
    public String root(HttpSession session) {
        // If already granted, go to app; otherwise go to access page
        Object granted = session.getAttribute(AccessController.SESSION_ACCESS_GRANTED);
        if (granted instanceof Boolean b && b) {
            return "redirect:/app";
        }
        return "redirect:/access";
    }

    @GetMapping("/access")
    public String accessPage(HttpSession session) {
        return "forward:/access.html";
    }

    @GetMapping("/app")
    public String appPage(HttpSession session, Model model) {
        String userName = (String) session.getAttribute(AccessController.SESSION_USER_NAME);
        if (userName == null || userName.isBlank()) {
            userName = normalizeUserName(System.getProperty("user.name", "Learner"));
            session.setAttribute(AccessController.SESSION_USER_NAME, userName);
        }
        model.addAttribute("userName", userName);
        model.addAttribute("welcomeQuote", "Small progress each day leads to big results.");
        return "index";
    }

    @GetMapping("/denied")
    public String deniedPage() {
        return "forward:/denied.html";
    }

    /** Lightweight health check endpoint used by Render and load balancers. */
    @GetMapping("/health")
    @ResponseBody
    public ResponseEntity<Map<String, String>> health() {
        return ResponseEntity.ok(Map.of("status", "UP"));
    }

    private String normalizeUserName(String rawName) {
        if (rawName == null || rawName.isBlank()) {
            return "Learner";
        }
        String trimmed = rawName.trim().replaceAll("\\s+", " ");
        if (trimmed.isEmpty()) {
            return "Learner";
        }
        return trimmed.substring(0, 1).toUpperCase(Locale.ROOT) + trimmed.substring(1);
    }
}
