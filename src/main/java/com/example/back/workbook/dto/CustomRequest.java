package com.example.back.workbook.dto;

import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Getter
public class CustomRequest {
    @NotBlank
    private String title;
    @NotEmpty
    private List<@NotBlank Long> selectedQuestionId;
}
