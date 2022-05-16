package com.example.back.question.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private QuestionSource questionSource;

    @Embedded
    private RemoteStoragePath remoteStoragePath;

    private byte point;

    private double answerRate;

    private short answer;

    private short tag;
}