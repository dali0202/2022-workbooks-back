package com.example.back.auth.auth.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class OAuthUriResponse {
    private String uri;
    @Builder
    public OAuthUriResponse(String uri) {
        this.uri = uri;
    }
}
