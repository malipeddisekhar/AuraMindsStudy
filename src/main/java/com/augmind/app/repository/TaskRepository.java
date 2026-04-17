package com.augmind.app.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.augmind.app.domain.TaskItem;

public interface TaskRepository extends JpaRepository<TaskItem, Long> {
    List<TaskItem> findAllByTaskDateOrderByCreatedAtDesc(LocalDate taskDate);
    List<TaskItem> findAllByTaskDateAndCompletedOrderByCreatedAtDesc(LocalDate taskDate, boolean completed);
    long countByTaskDateAndCompleted(LocalDate taskDate, boolean completed);
    long countByTaskDate(LocalDate taskDate);
    List<TaskItem> findAllByTaskDateIsNull();

    @Query("select distinct t.taskDate from TaskItem t order by t.taskDate desc")
    List<LocalDate> findDistinctTaskDatesDesc();
}
