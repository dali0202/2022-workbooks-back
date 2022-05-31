package com.example.back.workbook.dto;

import lombok.Getter;

import javax.validation.constraints.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
public class RangeRequest {
    @NotBlank
    private String title;
    @Positive
    private int questionNum;
    @NotNull
    private double lowerBound;
    @NotNull
    private double upperBound;
    @NotEmpty
    private List<@Positive Integer> selectedUnit;
    @NotEmpty
    private List<@Positive Integer> selectedPoint;
}