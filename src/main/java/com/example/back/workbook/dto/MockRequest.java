package com.example.back.workbook.dto;

import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
public class MockRequest {
    @NotBlank
    private String title;
    @NotBlank
    private int grade;
    @NotBlank
    private int month;
}