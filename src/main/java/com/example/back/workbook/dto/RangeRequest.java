package com.example.back.workbook.dto;

import lombok.Getter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
public class RangeRequest {
    private int questionNum;
    private int lowerBound;
    private int upperBound;
    private List<Integer> selectedUnit;
    private List<Integer> selectedPoint;
}
