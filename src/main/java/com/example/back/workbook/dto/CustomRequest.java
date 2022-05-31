package com.example.back.workbook.dto;

import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.util.List;

@Getter
public class CustomRequest {
    @NotBlank
    @Size(min = 1, max = 20)
    private String title;
    @NotEmpty
    private List<@Positive Long> selectedQuestionId;
}
