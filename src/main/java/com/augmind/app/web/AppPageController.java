package com.augmind.app.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpSession;

@Controller
public class AppPageController {

    @GetMapping("/")
    public String root() {
        return "redirect:/access";
    }

    @GetMapping("/access")
    public String accessPage(HttpSession session) {
        if (isGranted(session)) {
            return "redirect:/app";
        }
        return "forward:/access.html";
    }

    @GetMapping("/app")
    public String appPage(HttpSession session) {
        if (!isGranted(session)) {
            return "redirect:/denied?reason=unauthorized";
        }
        return "forward:/index.html";
    }

    @GetMapping("/denied")
    public String deniedPage() {
        return "forward:/denied.html";
    }

    private boolean isGranted(HttpSession session) {
        Object val = session.getAttribute(AccessController.SESSION_ACCESS_GRANTED);
        return val instanceof Boolean b && b;
    }
}
