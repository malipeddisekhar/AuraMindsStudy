package com.augmind.app.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;

@Entity
@Table(name = "subject_items")
public class SubjectItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = false, length = 8)
    private String emoji;

    @Column(name = "target_hours", nullable = false)
    private int targetHours;

    @Column(name = "logged_hours", nullable = false)
    private int loggedHours;

    @Column(nullable = false, length = 16)
    private String color;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "subject_date")
    private LocalDate subjectDate;

    @PrePersist
    void onCreate() {
        if (createdAt == null) {
            createdAt = LocalDateTime.now();
        }
        if (subjectDate == null) {
            subjectDate = createdAt.toLocalDate();
        }
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getEmoji() { return emoji; }
    public void setEmoji(String emoji) { this.emoji = emoji; }
    public int getTargetHours() { return targetHours; }
    public void setTargetHours(int targetHours) { this.targetHours = targetHours; }
    public int getLoggedHours() { return loggedHours; }
    public void setLoggedHours(int loggedHours) { this.loggedHours = loggedHours; }
    public String getColor() { return color; }
    public void setColor(String color) { this.color = color; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    public LocalDate getSubjectDate() { return subjectDate; }
    public void setSubjectDate(LocalDate subjectDate) { this.subjectDate = subjectDate; }
}
