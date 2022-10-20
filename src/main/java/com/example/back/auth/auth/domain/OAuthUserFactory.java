package com.example.back.auth.auth.domain;

import com.example.back.auth.auth.domain.impl.GoogleOAuthUser;
import com.example.back.auth.auth.domain.impl.KakaoOAuthUser;
import com.example.back.auth.auth.domain.impl.NaverOAuthUser;
import com.example.back.exception.AuthException;
import com.example.back.exception.ErrorCode;

import java.util.Map;

public class OAuthUserFactory {
    public static OAuthUser createOAuthUser(String provider, Map<String, Object> attributes) {
        if (provider.equals("kakao")) {
            return new KakaoOAuthUser(attributes);
        } else if (provider.equals("naver")) {
            return new NaverOAuthUser(attributes);
        } else if (provider.equals("google")) {
            return new GoogleOAuthUser(attributes);
        }
        throw new AuthException(ErrorCode.AUTH_ERROR);
    }
}
