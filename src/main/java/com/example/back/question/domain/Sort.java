package com.example.back.question.domain;

import com.querydsl.core.types.OrderSpecifier;
import lombok.Getter;

import static com.example.back.question.domain.QQuestion.question;

@Getter
public enum Sort {
    CREATED_ASC(question.questionSource.year.asc()),
    CREATED_DESC(question.questionSource.year.desc()),
    ANSWER_RATE_ASC(question.answerRate.asc()),
    ANSWER_RATE_DESC(question.answerRate.desc());

    private final OrderSpecifier sort;

    Sort(OrderSpecifier sort) {
        this.sort = sort;
    }
}
