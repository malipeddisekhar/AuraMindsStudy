package com.augmind.app.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.augmind.app.domain.SubjectItem;

public interface SubjectRepository extends JpaRepository<SubjectItem, Long> {
	List<SubjectItem> findAllBySubjectDateOrderByCreatedAtDesc(LocalDate subjectDate);
	List<SubjectItem> findAllBySubjectDateIsNull();
	long countBySubjectDate(LocalDate subjectDate);

	@Query("select distinct s.subjectDate from SubjectItem s order by s.subjectDate desc")
	List<LocalDate> findDistinctSubjectDatesDesc();
}
