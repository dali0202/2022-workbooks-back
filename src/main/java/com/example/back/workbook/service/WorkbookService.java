package com.example.back.workbook.service;

import com.example.back.question.domain.Question;
import com.example.back.question.domain.QuestionRepository;
import com.example.back.question.domain.QuestionRepositoryImpl;
import com.example.back.storage.domain.Storage;
import com.example.back.storage.domain.StorageRepository;
import com.example.back.user.domain.User;
import com.example.back.workbook.domain.Workbook;
import com.example.back.workbook.domain.WorkbookQuestion;
import com.example.back.workbook.domain.WorkbookQuestionRepository;
import com.example.back.workbook.domain.WorkbookRepository;
import com.example.back.workbook.dto.CustomRequest;
import com.example.back.workbook.dto.MockRequest;
import com.example.back.workbook.dto.RangeRequest;
import com.example.back.workbook.dto.WorkbookResponse;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor(access = AccessLevel.PUBLIC)
@Service
public class WorkbookService {
    private final WorkbookRepository workbookRepository;
    private final QuestionRepositoryImpl questionRepositoryImpl;
    private final StorageRepository storageRepository;

    private final WorkbookQuestionRepository workbookQuestionRepository;

    public List<WorkbookResponse> findAllBoards() {
        return(WorkbookResponse.listOf(workbookRepository.findAll()));
    }
    public void saveMock(User user, MockRequest mockRequest) {
        System.out.println("grade: " + mockRequest.getGrade());
        System.out.println("month: " + mockRequest.getMonth());
    }

    public void saveRange(User user, RangeRequest rangeRequest) {
        List<Question> questions = questionRepositoryImpl.searchQuestionByRange(rangeRequest);
        Workbook savedWorkbook = workbookRepository.save(Workbook
                .builder()
                .title(rangeRequest.getTitle())
                .user(user)
                .isMock(false)
                .build());
        int i = 1;
        for (Question question : questions) {
        workbookQuestionRepository.save(WorkbookQuestion.builder().question(question).workbook(savedWorkbook).num(i).build());
        i++;}
        saveWorkbookInStorage(user, savedWorkbook);
    }

    public void saveCustom(User user, CustomRequest customRequest) {
        Workbook savedWorkbook = workbookRepository.save(Workbook
                .builder()
                .title(customRequest.getTitle())
                .user(user)
                .isMock(false)
                .build());
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
