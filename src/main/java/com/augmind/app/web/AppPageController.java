package com.augmind.app.web;

import java.util.Locale;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpSession;

@Controller
public class AppPageController {

    @GetMapping("/")
    public String root() {
        return "redirect:/app";
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
