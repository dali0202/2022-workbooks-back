package com.example.back.workbook.domain;

import com.example.back.question.domain.QQuestion;
import com.example.back.question.domain.Question;
import com.example.back.question.domain.QuestionRepositoryCustom;
import com.example.back.workbook.dto.RangeRequest;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class WorkbookRepositoryImpl implements WorkbookRepositoryCustom {
    private final JPAQueryFactory queryFactory;

    public WorkbookRepositoryImpl(JPAQueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }

    QWorkbook workbook = QWorkbook.workbook;

    @Override
    public List<Workbook> searchWorkbook(String keyword, int page, int size) {
        return queryFactory
                .selectFrom(workbook)
                .offset(page)
                .limit(size)
                .where(workbook.title.contains(keyword))
                .orderBy(workbook.createdDate.desc())
                .fetch();
    }

    public List<Workbook> searchWorkbook2(String keyword, int lastWorkbookId, int size) {
        return queryFactory
                .selectFrom(workbook)
                .limit(size)
                .where(workbook.title.contains(keyword), workbook.id.lt(lastWorkbookId))
                .orderBy(workbook.createdDate.desc())
                .fetch();
    }
}