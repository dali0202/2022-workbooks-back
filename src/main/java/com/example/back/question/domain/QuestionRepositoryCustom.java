package com.example.back.question.domain;

import com.example.back.question.domain.Question;

import java.util.List;

public interface QuestionRepositoryCustom {
    List<Question> searchQuestion(byte grade, byte month, byte point);
}
