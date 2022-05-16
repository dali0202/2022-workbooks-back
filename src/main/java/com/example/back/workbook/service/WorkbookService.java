package com.example.back.workbook.service;

import com.example.back.user.domain.User;
import com.example.back.workbook.dto.MockRequest;
import com.example.back.workbook.dto.RangeRequest;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor(access = AccessLevel.PUBLIC)
@Service
public class WorkbookService {

    public void saveMock(User user, MockRequest mockRequest) {
        System.out.println("grade: " + mockRequest.getGrade());
        System.out.println("month: " + mockRequest.getMonth());
    }

    public void saveRange(User user, RangeRequest rangeRequest) {
        System.out.println("num: " + rangeRequest.getQuestionNum());
        System.out.println("upper bound: " + rangeRequest.getLowerBound());
        System.out.println("lower bound: " + rangeRequest.getUpperBound());
        System.out.println("unit: " + rangeRequest.getSelectedUnit());
        System.out.println("point: " + rangeRequest.getSelectedPoint());
    }
}
