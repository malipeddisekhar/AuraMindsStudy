package com.augmind.app.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.augmind.app.dto.StatsResponse;
import com.augmind.app.service.StatsService;

@RestController
@RequestMapping("/stats")
public class StatsController {

    private final StatsService statsService;

    public StatsController(StatsService statsService) {
        this.statsService = statsService;
    }

    @GetMapping
    public StatsResponse getStats() {
        return statsService.touchDailyActivity();
    }

    @PostMapping("/sessions/increment")
    public StatsResponse incrementSession() {
        return statsService.incrementSessions();
    }
}
