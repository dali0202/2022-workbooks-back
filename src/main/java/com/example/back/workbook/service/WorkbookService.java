package com.example.back.workbook.service;

import com.example.back.storage.domain.Storage;
import com.example.back.storage.domain.StorageRepository;
import com.example.back.user.domain.User;
import com.example.back.workbook.domain.Workbook;
import com.example.back.workbook.domain.WorkbookRepository;
import com.example.back.workbook.dto.CustomRequest;
import com.example.back.workbook.dto.MockRequest;
import com.example.back.workbook.dto.RangeRequest;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor(access = AccessLevel.PUBLIC)
@Service
public class WorkbookService {
    private final WorkbookRepository workbookRepository;
    private final StorageRepository storageRepository;

    //private final workbookQuestionRepository workbookQuestionRepository;
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

    public void saveCustom(User user, CustomRequest customRequest) {
        Workbook savedWorkbook = workbookRepository.save(Workbook
                .builder()
                .user(user)
                .isMock(false)
                .build());
        // 문제집에 문제 추가과정
        saveWorkbookInStorage(user, savedWorkbook);
    }

    // 만들어진 Workbook 엔티티를 불필요하게 dto로 변환하여 Storage의 서비스로 전달하기보다 여기서 처리.
    private void saveWorkbookInStorage(User user, Workbook workbook) {
        storageRepository.save(Storage
                .builder()
                .user(user)
                .workbook(workbook)
                .build());
    }
}
