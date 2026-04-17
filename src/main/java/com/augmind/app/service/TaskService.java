package com.augmind.app.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.augmind.app.domain.TaskItem;
import com.augmind.app.dto.TaskCreateRequest;
import com.augmind.app.dto.TaskHistoryDayResponse;
import com.augmind.app.dto.TaskResponse;
import com.augmind.app.repository.TaskRepository;

import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityNotFoundException;

@Service
@Transactional
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @PostConstruct
    public void backfillTaskDates() {
        List<TaskItem> withoutDate = taskRepository.findAllByTaskDateIsNull();
        if (withoutDate.isEmpty()) {
            return;
        }
        for (TaskItem item : withoutDate) {
            if (item.getCreatedAt() != null) {
                item.setTaskDate(item.getCreatedAt().toLocalDate());
            } else {
                item.setTaskDate(LocalDate.now());
            }
        }
        taskRepository.saveAll(withoutDate);
    }

    @Transactional(readOnly = true)
    public List<TaskResponse> listTasks(String status) {
        return listTasksByDate(LocalDate.now(), status);
    }

    @Transactional(readOnly = true)
    public List<TaskResponse> listTasksByDate(LocalDate date, String status) {
        List<TaskItem> items;
        if ("active".equalsIgnoreCase(status)) {
            items = taskRepository.findAllByTaskDateAndCompletedOrderByCreatedAtDesc(date, false);
        } else if ("completed".equalsIgnoreCase(status)) {
            items = taskRepository.findAllByTaskDateAndCompletedOrderByCreatedAtDesc(date, true);
        } else {
            items = taskRepository.findAllByTaskDateOrderByCreatedAtDesc(date);
        }
        return items.stream()
            .map(this::toResponse)
            .toList();
    }

    @Transactional(readOnly = true)
    public List<TaskHistoryDayResponse> listTaskHistorySummary() {
        return taskRepository.findDistinctTaskDatesDesc().stream()
            .map(date -> new TaskHistoryDayResponse(
                date,
                taskRepository.countByTaskDate(date),
                taskRepository.countByTaskDateAndCompleted(date, true)
            ))
            .toList();
    }

    public TaskResponse createTask(TaskCreateRequest request) {
        TaskItem task = new TaskItem();
        task.setText(request.text().trim());
        task.setPriority(request.priority());
        task.setCompleted(false);
        task.setTaskDate(LocalDate.now());
        return toResponse(taskRepository.save(task));
    }

    public TaskResponse toggleTask(Long id) {
        TaskItem task = taskRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Task not found: " + id));
        task.setCompleted(!task.isCompleted());
        return toResponse(taskRepository.save(task));
    }

    public void deleteTask(Long id) {
        if (!taskRepository.existsById(id)) {
            throw new EntityNotFoundException("Task not found: " + id);
        }
        taskRepository.deleteById(id);
    }

    private TaskResponse toResponse(TaskItem item) {
        return new TaskResponse(item.getId(), item.getText(), item.getPriority(), item.isCompleted(), item.getCreatedAt());
    }
}
