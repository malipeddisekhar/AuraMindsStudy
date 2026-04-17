package com.augmind.app.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.augmind.app.domain.SubjectItem;
import com.augmind.app.dto.SubjectCreateRequest;
import com.augmind.app.dto.SubjectHistoryDayResponse;
import com.augmind.app.dto.SubjectResponse;
import com.augmind.app.repository.SubjectRepository;

import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityNotFoundException;

@Service
@Transactional
public class SubjectService {

    private final SubjectRepository subjectRepository;

    public SubjectService(SubjectRepository subjectRepository) {
        this.subjectRepository = subjectRepository;
    }

    @PostConstruct
    public void backfillSubjectDates() {
        List<SubjectItem> withoutDate = subjectRepository.findAllBySubjectDateIsNull();
        if (withoutDate.isEmpty()) {
            return;
        }
        for (SubjectItem item : withoutDate) {
            if (item.getCreatedAt() == null) {
                item.setCreatedAt(LocalDateTime.now());
            }
            item.setSubjectDate(item.getCreatedAt().toLocalDate());
        }
        subjectRepository.saveAll(withoutDate);
    }

    @Transactional(readOnly = true)
    public List<SubjectResponse> listSubjects() {
        return subjectRepository.findAll().stream().map(this::toResponse).toList();
    }

    @Transactional(readOnly = true)
    public List<SubjectResponse> listSubjectsByDate(LocalDate date) {
        return subjectRepository.findAllBySubjectDateOrderByCreatedAtDesc(date).stream().map(this::toResponse).toList();
    }

    @Transactional(readOnly = true)
    public List<SubjectHistoryDayResponse> listSubjectHistorySummary() {
        return subjectRepository.findDistinctSubjectDatesDesc().stream()
            .map(date -> new SubjectHistoryDayResponse(date, subjectRepository.countBySubjectDate(date)))
            .toList();
    }

    public SubjectResponse createSubject(SubjectCreateRequest request) {
        SubjectItem item = new SubjectItem();
        item.setName(request.name().trim());
        item.setEmoji(request.emoji().trim());
        item.setTargetHours(request.targetHours());
        item.setLoggedHours(0);
        item.setColor(request.color());
        item.setCreatedAt(LocalDateTime.now());
        item.setSubjectDate(LocalDate.now());
        return toResponse(subjectRepository.save(item));
    }

    public void deleteSubject(Long id) {
        if (!subjectRepository.existsById(id)) {
            throw new EntityNotFoundException("Subject not found: " + id);
        }
        subjectRepository.deleteById(id);
    }

    private SubjectResponse toResponse(SubjectItem item) {
        return new SubjectResponse(
            item.getId(),
            item.getName(),
            item.getEmoji(),
            item.getTargetHours(),
            item.getLoggedHours(),
            item.getColor(),
            item.getCreatedAt(),
            item.getSubjectDate()
        );
    }
}
