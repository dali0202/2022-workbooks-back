package com.example.back.question.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@NoArgsConstructor
@Getter
@Embeddable
public class QuestionSource {
    private byte grade;
    private short year;
    private byte month;
    private byte num;

    public QuestionSource(byte grade, short year, byte month, byte num) {
        this.grade = grade;
        this.year = year;
        this.month = month;
        this.num = num;
    }
}
