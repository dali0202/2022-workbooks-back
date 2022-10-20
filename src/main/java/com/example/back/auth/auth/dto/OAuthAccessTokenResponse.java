package com.example.back.auth.auth.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class OAuthAccessTokenResponse {
    private String id_token;
}
