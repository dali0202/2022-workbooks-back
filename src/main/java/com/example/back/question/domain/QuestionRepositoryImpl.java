package com.example.back.question.domain;

import com.example.back.workbook.domain.QWorkbook;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class QuestionRepositoryImpl implements QuestionRepositoryCustom {
    private final JPAQueryFactory queryFactory;

    public QuestionRepositoryImpl(JPAQueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }

    QQuestion question = QQuestion.question;

    @Override
    public List<Question> searchQuestion(byte grade, byte month, byte point) {
        return queryFactory
                .selectFrom(question)
                .where(gradeEquals(grade),
                        monthEquals(month),
                        pointEquals(point))
                .fetch();
    }
    private BooleanExpression gradeEquals(byte grade) {
        return (grade != 0) ? question.questionSource.grade.eq(grade) : null;
    }
    private BooleanExpression monthEquals(byte month) {
        return (month != 0) ? question.questionSource.month.eq(month) : null;
    }
    private BooleanExpression pointEquals(byte point) {
        return (point != 0) ? question.point.eq(point) : null;
    }
}
