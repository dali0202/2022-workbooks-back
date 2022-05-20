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
            @RequestParam byte grade, @RequestParam byte month, @RequestParam byte point, @RequestParam int page, @RequestParam int size, @RequestParam Sort sort) {
        return ResponseEntity.ok(questionService.findByCondition(grade, month, point, page, size, sort));
    }
}
