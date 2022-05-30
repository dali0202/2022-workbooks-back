package com.example.back.workbook.dto;

import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
public class RangeRequest {
    @NotBlank
    private String title;
    private int questionNum;
    private int lowerBound;
    private int upperBound;
    @NotEmpty
    private List<@NotBlank Integer> selectedUnit;
    @NotEmpty
    private List<@NotBlank Integer> selectedPoint;
}
