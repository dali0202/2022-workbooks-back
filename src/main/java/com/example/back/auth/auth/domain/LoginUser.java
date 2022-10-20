package com.example.back.auth.auth.domain;

import lombok.Builder;
import lombok.Getter;
@Getter
public class LoginUser {
    private Long id;
    @Builder
    public LoginUser(Long id) {
        this.id = id;
    }
}
