package com.example.back.workbook.domain;

import com.example.back.common.domain.BaseTimeEntity;
import com.example.back.user.domain.User;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

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

    private boolean isMock;

    private String title;

    @Builder
    public Workbook(User user, boolean isMock, String title) {
        this.user = user;
        this.isMock = isMock;
        this.title = title;
    }
}
