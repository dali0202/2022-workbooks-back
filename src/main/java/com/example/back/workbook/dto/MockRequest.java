package com.example.back.workbook.dto;

import lombok.Getter;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

@Getter
public class MockRequest {
    @NotBlank
    @Size(min = 1, max = 20)
    private String title;
    @Positive
    private int grade;
    @Positive
    private int month;
}