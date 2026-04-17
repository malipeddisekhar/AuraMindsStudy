package com.augmind.app.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDate;

@Entity
@Table(name = "user_metrics")
public class UserMetrics {

    @Id
    private Long id;

    @Column(name = "sessions_completed", nullable = false)
    private int sessionsCompleted;

    @Column(nullable = false)
    private int streak;

    @Column(name = "last_active", nullable = false)
    private LocalDate lastActive;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public int getSessionsCompleted() { return sessionsCompleted; }
    public void setSessionsCompleted(int sessionsCompleted) { this.sessionsCompleted = sessionsCompleted; }
    public int getStreak() { return streak; }
    public void setStreak(int streak) { this.streak = streak; }
    public LocalDate getLastActive() { return lastActive; }
    public void setLastActive(LocalDate lastActive) { this.lastActive = lastActive; }
}
