package com.augmind.app.web;

import java.time.LocalDate;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.augmind.app.dto.TaskCreateRequest;
import com.augmind.app.dto.TaskHistoryDayResponse;
import com.augmind.app.dto.TaskResponse;
import com.augmind.app.service.TaskService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public List<TaskResponse> getTasks(@RequestParam(defaultValue = "all") String status) {
        return taskService.listTasks(status);
    }

    @GetMapping("/history/summary")
    public List<TaskHistoryDayResponse> getTaskHistorySummary() {
        return taskService.listTaskHistorySummary();
    }

    @GetMapping("/history/{date}")
    public List<TaskResponse> getTasksByDate(
        @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
        @RequestParam(defaultValue = "all") String status
    ) {
        return taskService.listTasksByDate(date, status);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TaskResponse createTask(@Valid @RequestBody TaskCreateRequest request) {
        return taskService.createTask(request);
    }

    @PatchMapping("/{id}/toggle")
    public TaskResponse toggleTask(@PathVariable Long id) {
        return taskService.toggleTask(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
    }
}
