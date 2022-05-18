package com.example.back.workbook.dto;

import com.example.back.question.domain.Question;
import com.example.back.question.dto.QuestionResponse;
import com.example.back.workbook.domain.Workbook;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class WorkbookDetailResponse {
    private Long id;
    private String title;
    private String userName;
    private List<QuestionResponse> questionResponses;

    @Builder
    public WorkbookDetailResponse(Long id, String title, String userName, List<QuestionResponse> questionResponses) {
        this.id = id;
        this.title = title;
        this.userName = userName;
        this.questionResponses = questionResponses;
    }

    public static WorkbookDetailResponse of(Workbook workbook, List<Question> questions) {
        return WorkbookDetailResponse
                .builder()
                .id(workbook.getId())
                .title(workbook.getTitle())
                .userName(workbook.getUser().getName())
                .questionResponses(QuestionResponse.listOf(questions))
                .build();
    }
}
