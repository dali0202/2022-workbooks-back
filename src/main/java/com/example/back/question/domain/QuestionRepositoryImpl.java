package com.example.back.question.domain;

import com.example.back.workbook.domain.QWorkbook;
import com.example.back.workbook.dto.RangeRequest;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.NumberExpression;
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
    public List<Question> searchQuestion(byte grade, byte month, int point, int page, int size, OrderSpecifier orderSpecifier) {
        return queryFactory
                .selectFrom(question)
                .offset(page * size)
                .limit(size)
                .orderBy(orderSpecifier)
                .where(gradeEquals(grade),
                        monthEquals(month),
                        pointEquals(point))
                .fetch();
    }
    @Override
    public List<Question> searchQuestionByRange(RangeRequest request) {
        return queryFactory
                .selectFrom(question)
                .where(rangeSearchPredicate(request))
                .orderBy(Expressions.numberTemplate(Double.class, "RAND()").asc())
                .limit(request.getQuestionNum())
                .fetch();
    }

    private BooleanExpression gradeEquals(byte grade) {
        return (grade != 0) ? question.questionSource.grade.eq(grade) : null;
    }
    private BooleanExpression monthEquals(byte month) {
        return (month != 0) ? question.questionSource.month.eq(month) : null;
    }
    private BooleanExpression pointEquals(int point) {
        return (point != 0) ? question.point.eq(point) : null;
    }
    private BooleanBuilder rangeSearchPredicate(RangeRequest request) {
        return new BooleanBuilder()
                .and(answerRateCondition(request.getLowerBound(), request.getUpperBound()))
                .and(unitCondition(request.getSelectedUnit()))
                .and(pointCondition(request.getSelectedPoint()));
    }

    private BooleanBuilder answerRateCondition(double lowerBound, double upperBound) {
        return new BooleanBuilder().and(question.answerRate.between(lowerBound, upperBound));
    }
    private BooleanBuilder unitCondition(List<Integer> selectedUnit) {
        BooleanBuilder builder = new BooleanBuilder();
        selectedUnit.stream().forEach(u -> builder.or(question.unit.eq(u)));
        return builder;
    }
    private BooleanBuilder pointCondition(List<Integer> selectedPoint) {
        BooleanBuilder builder = new BooleanBuilder();
        selectedPoint.stream().forEach(u -> builder.or(question.point.eq(u)));
        return builder;
    }
}