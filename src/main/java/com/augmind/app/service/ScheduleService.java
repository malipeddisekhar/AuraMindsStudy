package com.augmind.app.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.augmind.app.domain.ScheduleItem;
import com.augmind.app.dto.ScheduleCreateRequest;
import com.augmind.app.dto.ScheduleHistoryDayResponse;
import com.augmind.app.dto.ScheduleResponse;
import com.augmind.app.repository.ScheduleRepository;

import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityNotFoundException;

@Service
@Transactional
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;

    public ScheduleService(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }

    @PostConstruct
    public void backfillScheduleDates() {
        List<ScheduleItem> withoutDate = scheduleRepository.findAllByScheduleDateIsNull();
        if (withoutDate.isEmpty()) {
            return;
        }
        for (ScheduleItem item : withoutDate) {
            item.setScheduleDate(LocalDate.now());
        }
        scheduleRepository.saveAll(withoutDate);
    }

    @Transactional(readOnly = true)
    public List<ScheduleResponse> listSchedule() {
        return listScheduleByDate(LocalDate.now());
    }

    @Transactional(readOnly = true)
    public List<ScheduleResponse> listScheduleByDate(LocalDate date) {
        return scheduleRepository.findAllByScheduleDateOrderByTimeAsc(date).stream().map(this::toResponse).toList();
    }

    @Transactional(readOnly = true)
    public List<ScheduleHistoryDayResponse> listScheduleHistorySummary() {
        return scheduleRepository.findDistinctScheduleDatesDesc().stream()
            .map(date -> new ScheduleHistoryDayResponse(date, scheduleRepository.countByScheduleDate(date)))
            .toList();
    }

    public ScheduleResponse createSchedule(ScheduleCreateRequest request) {
        ScheduleItem item = new ScheduleItem();
        item.setScheduleDate(request.date() == null ? LocalDate.now() : request.date());
        item.setTime(request.time());
        item.setTitle(request.title().trim());
        item.setDescription(request.description().trim());
        item.setColor(request.color());
        return toResponse(scheduleRepository.save(item));
    }

    public void deleteSchedule(Long id) {
        if (!scheduleRepository.existsById(id)) {
            throw new EntityNotFoundException("Schedule item not found: " + id);
        }
        scheduleRepository.deleteById(id);
    }

    private ScheduleResponse toResponse(ScheduleItem item) {
        return new ScheduleResponse(item.getId(), item.getScheduleDate(), item.getTime(), item.getTitle(), item.getDescription(), item.getColor());
    }
}
