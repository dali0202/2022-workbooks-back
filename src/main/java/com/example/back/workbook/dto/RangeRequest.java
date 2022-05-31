package com.example.back.workbook.dto;

import lombok.Getter;

import javax.validation.constraints.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
public class RangeRequest {
    @NotBlank
    @Size(min = 1, max = 20)
    private String title;
    @Positive
    @Max(100)
    private int questionNum;
    @NotNull
    private double lowerBound;
    @NotNull
    private double upperBound;
    @NotEmpty
    private List<@PositiveOrZero Integer> selectedUnit;
    @NotEmpty
    private List<@PositiveOrZero Integer> selectedPoint;
}