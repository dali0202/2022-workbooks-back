package com.example.back.workbook.dto;

import lombok.Getter;

import java.util.List;

@Getter
public class CustomRequest {
    private List<Integer> selectedQuestionId;
}
