package com.augmind.app.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.augmind.app.domain.NoteItem;

public interface NoteRepository extends JpaRepository<NoteItem, Long> {
    List<NoteItem> findTop10ByOrderByCreatedAtDesc();
    List<NoteItem> findAllByNoteDateOrderByCreatedAtDesc(LocalDate noteDate);
    List<NoteItem> findAllByNoteDateIsNull();
    long countByNoteDate(LocalDate noteDate);

    @Query("select distinct n.noteDate from NoteItem n order by n.noteDate desc")
    List<LocalDate> findDistinctNoteDatesDesc();
}
