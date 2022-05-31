package com.example.back.workbook.dto;

import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

@Getter
public class MockRequest {
    @NotBlank
    private String title;
    @Positive
    private int grade;
    @Positive
    private int month;
}