package com.example.back.workbook.dto;

import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
public class RangeRequest {
    @NotBlank
    private String title;
    @Positive
    private int questionNum;
    @NotBlank
    private double lowerBound;
    @NotBlank
    private double upperBound;
    @NotEmpty
    private List<@NotBlank Integer> selectedUnit;
    @NotEmpty
    private List<@NotBlank Integer> selectedPoint;
}
