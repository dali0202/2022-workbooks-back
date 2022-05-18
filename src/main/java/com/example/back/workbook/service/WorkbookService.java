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
    private final QuestionRepository questionRepository;
    private final QuestionRepositoryImpl questionRepositoryImpl;
    private final StorageRepository storageRepository;
    private final WorkbookQuestionRepository workbookQuestionRepository;

    public List<WorkbookResponse> findAllWorkbooks() {
        return(WorkbookResponse.listOf(workbookRepository.findAll()));
    }
    public void saveMock(User user, MockRequest mockRequest) {
        Workbook workbook = createDefaultWorkbook(mockRequest.getTitle(), user, true);
    }

    public void saveRange(User user, RangeRequest rangeRequest) {
        Workbook workbook = createDefaultWorkbook(rangeRequest.getTitle(), user, false);
        List<Question> questions = questionRepositoryImpl.searchQuestionByRange(rangeRequest);
        saveQuestionInWorkbook(workbook, questions);
        saveWorkbookInStorage(user, workbook);
    }
    public void saveCustom(User user, CustomRequest customRequest) {
        Workbook workbook = createDefaultWorkbook(customRequest.getTitle(), user, false);
        List<Question> questions = questionRepository.findAllById(customRequest.getSelectedQuestionId());
        saveQuestionInWorkbook(workbook, questions);
        saveWorkbookInStorage(user, workbook);
    }

    // 만들어진 Workbook 엔티티를 불필요하게 dto로 변환하여 Storage의 서비스로 전달하기보다 여기서 처리.
    // User에 양방향 걸고 편의메서드 만들어놓고 workbook 인자로 받으면 로직 처리가능할듯.
    // ex. user.addWorkbook(workbook)
    private void saveWorkbookInStorage(User user, Workbook workbook) {
        storageRepository.save(Storage
                .builder()
                .user(user)
                .workbook(workbook)
                .build());
    }
    private Workbook createDefaultWorkbook(String title, User user, boolean isMock) {
        return Workbook
                .builder()
                .title(title)
                .user(user)
                .isMock(isMock)
                .build();
    }
    private void saveQuestionInWorkbook(Workbook workbook, List<Question> questions) {
        for (int num = 0; num < questions.size(); num++) {
            workbookQuestionRepository.save(
                    WorkbookQuestion
                            .builder()
                            .question(questions.get(num))
                            .workbook(workbook)
                            .num(num + 1)
                            .build());
        }
    }
}
