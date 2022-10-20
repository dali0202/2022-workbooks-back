package com.example.back.auth.auth.properties;

import com.example.back.exception.AuthException;
import com.example.back.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class ProviderPropertiesFactory {
    private final GoogleProperties googleProperties;
    private final KakaoProperties kakaoProperties;
    private final NaverProperties naverProperties;

    public ProviderProperties getProviderProperties(String provider) {
        if (provider.equals("google")) {
            return googleProperties;
        } else if (provider.equals("kakao")) {
            return kakaoProperties;
        } else if (provider.equals("naver")) {
            return naverProperties;
        } else {
            throw new AuthException(ErrorCode.OAUTH2_PROVIDER_NOT_EXIST);
        }
    }
}
