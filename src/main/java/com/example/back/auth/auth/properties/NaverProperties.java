package com.example.back.auth.auth.properties;

import com.example.back.auth.auth.properties.ProviderProperties;
import com.example.back.config.AppProperties;
import lombok.Getter;
import org.springframework.stereotype.Component;

@Getter
@Component
public class NaverProperties extends ProviderProperties {
    private final String provider;
    private final String clientId;
    private final String clientSecret;
    private final String redirectUri;
    private final String tokenUri;
    private final String authorizationGrantType;
    private final String authorizationUri;

    public NaverProperties(AppProperties appProperties) {
        this.provider = appProperties.getOauth().getGoogle().getProvider();
        this.clientId = appProperties.getOauth().getNaver().getClientId();
        this.clientSecret = appProperties.getOauth().getNaver().getClientSecret();
        this.redirectUri = appProperties.getOauth().getNaver().getRedirectUri();
        this.tokenUri = appProperties.getOauth().getNaver().getTokenUri();
        this.authorizationGrantType = appProperties.getOauth().getNaver().getAuthorizationGrantType();
        this.authorizationUri = appProperties.getOauth().getNaver().getAuthorizationUri();
    }
}
