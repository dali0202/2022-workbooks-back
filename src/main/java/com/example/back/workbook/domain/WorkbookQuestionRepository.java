package com.example.back.workbook.domain;


import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WorkbookQuestionRepository extends JpaRepository<WorkbookQuestion, Long> {
    List<WorkbookQuestion> findByWorkbook(Workbook workbook);
}
