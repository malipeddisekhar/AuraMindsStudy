package com.augmind.app.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.augmind.app.domain.ScheduleItem;

public interface ScheduleRepository extends JpaRepository<ScheduleItem, Long> {
    List<ScheduleItem> findAllByScheduleDateOrderByTimeAsc(LocalDate scheduleDate);
    List<ScheduleItem> findAllByScheduleDateIsNull();
    long countByScheduleDate(LocalDate scheduleDate);

    @Query("select distinct s.scheduleDate from ScheduleItem s order by s.scheduleDate desc")
    List<LocalDate> findDistinctScheduleDatesDesc();
}
