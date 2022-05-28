package com.example.back.question.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@NoArgsConstructor
@Getter
@Embeddable
public class QuestionSource {
    private int grade;
    private int year;
    private int month;
    private int num;
    @Builder
    public QuestionSource(int grade, int year, int month, int num) {
        this.grade = grade;
        this.year = year;
        this.month = month;
        this.num = num;
    }
}
