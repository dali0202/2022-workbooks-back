package com.example.back.workbook.domain;

import com.example.back.question.domain.Question;
import com.example.back.workbook.dto.RangeRequest;
import com.querydsl.core.types.OrderSpecifier;

import java.util.List;

public interface WorkbookRepositoryCustom {
    List<Workbook> searchWorkbook(String keyword, int lastWorkbookId, int size);
}
