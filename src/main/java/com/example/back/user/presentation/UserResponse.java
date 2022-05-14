package com.example.back.user.presentation;

import com.example.back.user.domain.User;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserResponse {
    private String name;

    @Builder
    public UserResponse(String name) {
        this.name = name;
    }

    public static UserResponse of(User user) {
        return UserResponse
                .builder()
                .name(user.getName())
                .build();
    }
}
