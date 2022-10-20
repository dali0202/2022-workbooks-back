package com.example.back.workbook.domain;

import com.example.back.question.domain.QQuestion;
import com.example.back.question.domain.Question;
import com.example.back.question.domain.QuestionRepositoryCustom;
import com.example.back.workbook.dto.RangeRequest;
//import com.nimbusds.oauth2.sdk.util.StringUtils;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.util.StringUtils;
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

    public List<Workbook> searchWorkbook(String keyword, int lastWorkbookId, int size) {
        return queryFactory
                .selectFrom(workbook)
                .limit(size)
                .where(keywordContains(keyword), workbookIdLessThan(lastWorkbookId))
                .orderBy(workbook.createdDate.desc())
                .join(workbook.user)
                .fetchJoin()
                .fetch();
    }
    private BooleanExpression keywordContains(String keyword) {
        return !StringUtils.isNullOrEmpty(keyword) ? workbook.title.contains(keyword) : null;
    }
    private BooleanExpression workbookIdLessThan(int lastWorkbookId) {
        return (lastWorkbookId != 0) ? workbook.id.lt(lastWorkbookId) : null;
    }
}