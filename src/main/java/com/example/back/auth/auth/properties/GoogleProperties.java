package com.example.back.auth.auth.properties;

import com.example.back.auth.auth.properties.ProviderProperties;
import com.example.back.config.AppProperties;
import lombok.Getter;
import org.springframework.stereotype.Component;
@Getter
@Component
public class GoogleProperties extends ProviderProperties {
    private final String provider;
    private final String clientId;
    private final String clientSecret;
    private final String redirectUri;
    private final String tokenUri;
    private final String authorizationGrantType;
    private final String authorizationUri;

    public GoogleProperties(AppProperties appProperties) {
        this.provider = appProperties.getOauth().getGoogle().getProvider();
        this.clientId = appProperties.getOauth().getGoogle().getClientId();
        this.clientSecret = appProperties.getOauth().getGoogle().getClientSecret();
        this.redirectUri = appProperties.getOauth().getGoogle().getRedirectUri();
        this.tokenUri = appProperties.getOauth().getGoogle().getTokenUri();
        this.authorizationGrantType = appProperties.getOauth().getGoogle().getAuthorizationGrantType();
        this.authorizationUri = appProperties.getOauth().getGoogle().getAuthorizationUri();
    }
}
