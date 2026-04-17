package com.augmind.app.config;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.augmind.app.domain.ScheduleItem;
import com.augmind.app.domain.SubjectItem;
import com.augmind.app.domain.UserMetrics;
import com.augmind.app.repository.ScheduleRepository;
import com.augmind.app.repository.SubjectRepository;
import com.augmind.app.repository.UserMetricsRepository;

@Component
public class DataInitializer implements CommandLineRunner {

    private final SubjectRepository subjectRepository;
    private final ScheduleRepository scheduleRepository;
    private final UserMetricsRepository userMetricsRepository;

    public DataInitializer(
        SubjectRepository subjectRepository,
        ScheduleRepository scheduleRepository,
        UserMetricsRepository userMetricsRepository
    ) {
        this.subjectRepository = subjectRepository;
        this.scheduleRepository = scheduleRepository;
        this.userMetricsRepository = userMetricsRepository;
    }

    @Override
    public void run(String... args) {
        seedMetrics();
        seedSubjects();
        seedSchedule();
    }

    private void seedMetrics() {
        if (userMetricsRepository.existsById(1L)) {
            return;
        }
        UserMetrics metrics = new UserMetrics();
        metrics.setId(1L);
        metrics.setSessionsCompleted(0);
        metrics.setStreak(1);
        metrics.setLastActive(LocalDate.now());
        userMetricsRepository.save(metrics);
    }

    private void seedSubjects() {
        if (subjectRepository.count() > 0) {
            return;
        }

        SubjectItem math = new SubjectItem();
        math.setName("Mathematics");
        math.setEmoji("📐");
        math.setTargetHours(20);
        math.setLoggedHours(12);
        math.setColor("#4A7BF7");

        SubjectItem physics = new SubjectItem();
        physics.setName("Physics");
        physics.setEmoji("⚡");
        physics.setTargetHours(15);
        physics.setLoggedHours(8);
        physics.setColor("#F59E42");

        SubjectItem cs = new SubjectItem();
        cs.setName("Computer Science");
        cs.setEmoji("💻");
        cs.setTargetHours(25);
        cs.setLoggedHours(18);
        cs.setColor("#34C77B");

        SubjectItem literature = new SubjectItem();
        literature.setName("Literature");
        literature.setEmoji("📖");
        literature.setTargetHours(10);
        literature.setLoggedHours(5);
        literature.setColor("#8B5CF6");

        subjectRepository.saveAll(List.of(math, physics, cs, literature));
    }

    private void seedSchedule() {
        if (scheduleRepository.count() > 0) {
            return;
        }

        scheduleRepository.saveAll(List.of(
            schedule("08:00", "Morning Review", "Review yesterday's notes", "#34C77B"),
            schedule("09:30", "Math Lecture", "Chapter 7 - Differential Equations", "#4A7BF7"),
            schedule("11:00", "Physics Lab", "Electromagnetic experiments", "#F59E42"),
            schedule("13:00", "Lunch Break", "Rest and recharge", "#34C77B"),
            schedule("14:30", "CS Project", "Work on full-stack assignment", "#8B5CF6"),
            schedule("16:00", "Literature Reading", "Chapters 12-14", "#E5484D"),
            schedule("18:00", "Exercise", "30 min cardio + stretching", "#34C77B"),
            schedule("20:00", "Evening Study", "Review and practice problems", "#4A7BF7")
        ));
    }

    private ScheduleItem schedule(String time, String title, String description, String color) {
        ScheduleItem item = new ScheduleItem();
        item.setTime(LocalTime.parse(time));
        item.setTitle(title);
        item.setDescription(description);
        item.setColor(color);
        return item;
    }
}
