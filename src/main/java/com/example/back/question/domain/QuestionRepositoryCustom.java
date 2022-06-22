package com.example.back.question.domain;

import com.example.back.question.domain.Question;
import com.example.back.workbook.dto.RangeRequest;
import com.querydsl.core.types.OrderSpecifier;

import java.util.List;

public interface QuestionRepositoryCustom {
    List<Question> searchQuestion(int grade, int month, int point, int offset, int size, OrderSpecifier orderSpecifier);
    List<Question> searchQuestionByRange(RangeRequest request);
}