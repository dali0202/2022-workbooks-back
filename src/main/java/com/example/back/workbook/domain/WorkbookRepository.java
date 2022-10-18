package com.example.back.workbook.domain;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface WorkbookRepository extends JpaRepository<Workbook, Long> {
    @Query("select w from Workbook w join fetch w.workbookQuestions q join fetch q.question where w.id = :id")
    Optional<Workbook> findByIdWithFetch(@Param("id") Long id);
}