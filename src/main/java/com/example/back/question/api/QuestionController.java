package com.example.back.question.api;

import com.example.back.question.domain.Sort;
import com.example.back.question.dto.QuestionResponse;
import com.example.back.question.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/questions")
public class QuestionController {
    private final QuestionService questionService;

    @GetMapping
    public ResponseEntity<List<QuestionResponse>> findQuestions(
            @RequestParam(defaultValue = "0") int grade, @RequestParam(defaultValue = "0") int month, @RequestParam(defaultValue = "0") int point, @RequestParam(defaultValue = "0") int offset, @RequestParam int size, @RequestParam Sort sort) {
        return ResponseEntity.ok(questionService.findByCondition(grade, month, point, offset, size, sort));
    }
}
