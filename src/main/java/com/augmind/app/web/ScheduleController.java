package com.augmind.app.web;

import java.time.LocalDate;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.augmind.app.dto.ScheduleCreateRequest;
import com.augmind.app.dto.ScheduleHistoryDayResponse;
import com.augmind.app.dto.ScheduleResponse;
import com.augmind.app.service.ScheduleService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/schedule")
public class ScheduleController {

    private final ScheduleService scheduleService;

    public ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    @GetMapping
    public List<ScheduleResponse> getSchedule() {
        return scheduleService.listSchedule();
    }

    @GetMapping("/history/summary")
    public List<ScheduleHistoryDayResponse> getScheduleHistorySummary() {
        return scheduleService.listScheduleHistorySummary();
    }

    @GetMapping("/history/{date}")
    public List<ScheduleResponse> getScheduleByDate(
        @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date
    ) {
        return scheduleService.listScheduleByDate(date);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ScheduleResponse createSchedule(@Valid @RequestBody ScheduleCreateRequest request) {
        return scheduleService.createSchedule(request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteSchedule(@PathVariable Long id) {
        scheduleService.deleteSchedule(id);
    }
}
