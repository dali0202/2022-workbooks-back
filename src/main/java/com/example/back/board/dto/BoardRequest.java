package com.example.back.board.dto;

import com.example.back.board.domain.Board;
import com.example.back.user.domain.User;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
public class BoardRequest {

    private String title;
    private String description;
    public Board toBoard(User user) {
        return Board
                .builder()
                .user(user)
                .title(title)
                .description(description)
                .build();
    }
}
