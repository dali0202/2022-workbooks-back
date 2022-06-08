package com.example.back.user.dto;

import com.example.back.user.domain.User;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserResponse {
    private String name;
    private String role;
    @Builder
    public UserResponse(String name, String role) {
        this.name = name;
        this.role = role;
    }

    public static UserResponse of(User user) {
        return UserResponse
                .builder()
                .name(user.getName())
                .role(user.getRole().name())
                .build();
    }
}
