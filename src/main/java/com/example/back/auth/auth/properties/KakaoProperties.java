package com.example.back.auth.auth.properties;

import com.example.back.auth.auth.properties.ProviderProperties;
import com.example.back.config.AppProperties;
import lombok.Getter;
import org.springframework.stereotype.Component;

@Getter
@Component
public class KakaoProperties extends ProviderProperties {
    private final String provider;
    private final String clientId;
    private final String clientSecret;
    private final String redirectUri;
    private final String tokenUri;
    private final String authorizationGrantType;
    private final String authorizationUri;

    public KakaoProperties(AppProperties appProperties) {
        this.provider = appProperties.getOauth().getKakao().getProvider();
        this.clientId = appProperties.getOauth().getKakao().getClientId();
        this.clientSecret = appProperties.getOauth().getKakao().getClientSecret();
        this.redirectUri = appProperties.getOauth().getKakao().getRedirectUri();
        this.tokenUri = appProperties.getOauth().getKakao().getTokenUri();
        this.authorizationGrantType = appProperties.getOauth().getKakao().getAuthorizationGrantType();
        this.authorizationUri = appProperties.getOauth().getKakao().getAuthorizationUri();
    }
}
