package com.example.back.question.service;

import com.example.back.question.domain.Question;
import com.example.back.question.domain.QuestionRepository;
import com.example.back.question.domain.QuestionRepositoryImpl;
import com.example.back.question.dto.QuestionResponse;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@RequiredArgsConstructor(access = AccessLevel.PUBLIC)
@Service
public class QuestionService {
    private final QuestionRepositoryImpl questionRepository;
    public List<QuestionResponse> findByCondition(byte grade, byte month, int point) {
        List<Question> questions = questionRepository.searchQuestion(grade, month, point);
        return QuestionResponse.listOf(questions);
    }
}
