package com.example.back.question.domain;

import com.example.back.question.domain.Question;
import com.example.back.workbook.dto.RangeRequest;

import java.util.List;

public interface QuestionRepositoryCustom {
    List<Question> searchQuestion(byte grade, byte month, int point);
    List<Question> searchQuestionByRange(RangeRequest request);
}
