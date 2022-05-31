package com.example.back.workbook.domain;

import com.example.back.common.domain.BaseTimeEntity;
import com.example.back.question.domain.Question;
import com.example.back.user.domain.User;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Workbook extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "workbook_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "workbook", cascade = CascadeType.ALL)
    private List<WorkbookQuestion> workbookQuestions = new ArrayList<>();

    private int type;
    private String title;

    @Builder
    public Workbook(User user, int type, String title) {
        this.user = user;
        this.type = type;
        this.title = title;
    }

    public List<Question> getQuestions() {
        List<Question> questions = new ArrayList();
        workbookQuestions.forEach(workbookQuestion -> questions.add(workbookQuestion.getQuestion()));
//        현재 한문제마다 한쿼리가 나감
        return questions;
    }
}
