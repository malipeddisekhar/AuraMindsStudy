package com.augmind.app.service;

import java.time.LocalDate;
import java.util.Objects;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.augmind.app.domain.UserMetrics;
import com.augmind.app.dto.StatsResponse;
import com.augmind.app.repository.TaskRepository;
import com.augmind.app.repository.UserMetricsRepository;

@Service
@Transactional
public class StatsService {

    private static final long SINGLE_USER_ID = 1L;

    private final TaskRepository taskRepository;
    private final UserMetricsRepository userMetricsRepository;

    public StatsService(TaskRepository taskRepository, UserMetricsRepository userMetricsRepository) {
        this.taskRepository = taskRepository;
        this.userMetricsRepository = userMetricsRepository;
    }

    @Transactional(readOnly = true)
    public StatsResponse getStats() {
        UserMetrics metrics = loadMetrics();
        long tasksDone = taskRepository.countByTaskDateAndCompleted(LocalDate.now(), true);
        int studyMinutes = metrics.getSessionsCompleted() * 25;
        int studyHours = studyMinutes / 60;
        return new StatsResponse(tasksDone, metrics.getSessionsCompleted(), studyMinutes, studyHours, metrics.getStreak());
    }

    public StatsResponse incrementSessions() {
        UserMetrics metrics = loadMetrics();
        metrics.setSessionsCompleted(metrics.getSessionsCompleted() + 1);
        updateStreak(metrics);
        userMetricsRepository.save(metrics);
        return getStats();
    }

    public StatsResponse touchDailyActivity() {
        UserMetrics metrics = loadMetrics();
        updateStreak(metrics);
        userMetricsRepository.save(Objects.requireNonNull(metrics));
        return getStats();
    }

    private UserMetrics loadMetrics() {
        return userMetricsRepository.findById(SINGLE_USER_ID)
            .orElseGet(() -> {
                UserMetrics metrics = new UserMetrics();
                metrics.setId(SINGLE_USER_ID);
                metrics.setSessionsCompleted(0);
                metrics.setStreak(1);
                metrics.setLastActive(LocalDate.now());
                return userMetricsRepository.save(metrics);
            });
    }

    private void updateStreak(UserMetrics metrics) {
        LocalDate today = LocalDate.now();
        LocalDate lastActive = metrics.getLastActive();

        if (lastActive == null) {
            metrics.setLastActive(today);
            metrics.setStreak(1);
            return;
        }

        if (lastActive.isEqual(today)) {
            return;
        }

        if (lastActive.plusDays(1).isEqual(today)) {
            metrics.setStreak(metrics.getStreak() + 1);
        } else {
            metrics.setStreak(1);
        }
        metrics.setLastActive(today);
    }
}
