package com.example.back.question.dto;

import com.example.back.question.domain.Question;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class QuestionResponse {
    private Long id;
    private double answerRate;
    private int point;
    private int grade;
    private int month;
    private int num;
    private int year;
    private int unit;
    private int answer;
    private String descriptionPath;
    private String solutionPath;

    @Builder
    public QuestionResponse(Long id, double answerRate, int point, int grade, int month, int num, int year, int unit, int answer, String descriptionPath, String solutionPath) {
        this.id = id;
        this.answerRate = answerRate;
        this.point = point;
        this.grade = grade;
        this.month = month;
        this.num = num;
        this.year = year;
        this.unit = unit;
        this.answer = answer;
        this.descriptionPath = descriptionPath;
        this.solutionPath = solutionPath;
    }


    private static QuestionResponse of(Question question) {
    return QuestionResponse.builder()
            .id(question.getId())
            .answerRate(question.getAnswerRate())
            .point(question.getPoint())
            .grade(question.getQuestionSource().getGrade())
            .month(question.getQuestionSource().getMonth())
            .num(question.getQuestionSource().getNum())
            .year(question.getQuestionSource().getYear())
            .unit(question.getUnit())
            .answer(question.getAnswer())
            .descriptionPath(question.getRemoteStoragePath().getDescriptionPath())
            .solutionPath(question.getRemoteStoragePath().getSolutionPath())
            .build();
}

    public static List<QuestionResponse> listOf(List<Question> questions) {
        List<QuestionResponse> questionResponses = new ArrayList<>();
        for (Question question : questions) {
            questionResponses.add(of(question));
        }
        return questionResponses;
    }
}
