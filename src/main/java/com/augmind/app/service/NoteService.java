package com.augmind.app.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.augmind.app.domain.NoteItem;
import com.augmind.app.dto.NoteCreateRequest;
import com.augmind.app.dto.NoteHistoryDayResponse;
import com.augmind.app.dto.NoteResponse;
import com.augmind.app.repository.NoteRepository;

import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityNotFoundException;

@Service
@Transactional
public class NoteService {

    private final NoteRepository noteRepository;

    public NoteService(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    @PostConstruct
    public void backfillNoteDates() {
        List<NoteItem> withoutDate = noteRepository.findAllByNoteDateIsNull();
        if (withoutDate.isEmpty()) {
            return;
        }
        for (NoteItem item : withoutDate) {
            if (item.getCreatedAt() != null) {
                item.setNoteDate(item.getCreatedAt().toLocalDate());
            } else {
                item.setNoteDate(LocalDate.now());
            }
        }
        noteRepository.saveAll(withoutDate);
    }

    @Transactional(readOnly = true)
    public List<NoteResponse> listNotes() {
        return noteRepository.findTop10ByOrderByCreatedAtDesc().stream().map(this::toResponse).toList();
    }

    @Transactional(readOnly = true)
    public List<NoteResponse> listNotesByDate(LocalDate date) {
        return noteRepository.findAllByNoteDateOrderByCreatedAtDesc(date).stream().map(this::toResponse).toList();
    }

    @Transactional(readOnly = true)
    public List<NoteHistoryDayResponse> listNoteHistorySummary() {
        return noteRepository.findDistinctNoteDatesDesc().stream()
            .map(date -> new NoteHistoryDayResponse(date, noteRepository.countByNoteDate(date)))
            .toList();
    }

    public NoteResponse createNote(NoteCreateRequest request) {
        NoteItem item = new NoteItem();
        item.setText(request.text().trim());
        item.setNoteDate(LocalDate.now());
        return toResponse(noteRepository.save(item));
    }

    public NoteResponse updateNote(Long id, NoteCreateRequest request) {
        NoteItem item = noteRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Note not found: " + id));
        item.setText(request.text().trim());
        return toResponse(noteRepository.save(item));
    }

    public void deleteNote(Long id) {
        if (!noteRepository.existsById(id)) {
            throw new EntityNotFoundException("Note not found: " + id);
        }
        noteRepository.deleteById(id);
    }

    private NoteResponse toResponse(NoteItem item) {
        return new NoteResponse(item.getId(), item.getText(), item.getCreatedAt());
    }
}
