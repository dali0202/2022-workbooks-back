package com.example.back.auth.auth.service;

import com.example.back.auth.auth.domain.OAuthUser;
import com.example.back.auth.auth.domain.OAuthUserFactory;
import com.example.back.auth.auth.dto.OAuthAccessTokenResponse;
import com.example.back.auth.auth.properties.ProviderProperties;
import com.example.back.auth.auth.dto.TokenRequest;
import com.example.back.util.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@RequiredArgsConstructor
@Component
public class OAuthService {

    private static final String GRANT_TYPE = "grant_type";
    private static final String CODE = "code";
    private static final String REDIRECT_URI = "redirect_uri";
    private static final String CLIENT_ID = "client_id";
    private static final String CLIENT_SECRET = "client_secret";
    private final RestTemplate restTemplate = new RestTemplate();
    private final JwtUtils jwtUtils;

    public OAuthUser getOAuthUser(ProviderProperties providerProperties, TokenRequest tokenRequest) {
        OAuthAccessTokenResponse tokenResponse = requestOAuthToken(providerProperties, tokenRequest);
        Map<String, Object> attributes = jwtUtils.parsePayload(tokenResponse);
        return OAuthUserFactory.createOAuthUser(providerProperties.getProvider(), attributes);
    }

    private OAuthAccessTokenResponse requestOAuthToken(ProviderProperties providerProperties, TokenRequest tokenRequest) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        MultiValueMap<String, String> formData = generateFormData(providerProperties, tokenRequest);
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(formData, headers);
        return requestPostAuthGrant(providerProperties, request).getBody();
    }

    private ResponseEntity<OAuthAccessTokenResponse> requestPostAuthGrant(ProviderProperties providerProperties, final HttpEntity<MultiValueMap<String, String>> request) {
        return restTemplate.postForEntity(providerProperties.getTokenUri(), request, OAuthAccessTokenResponse.class);
    }

    private MultiValueMap<String, String> generateFormData(ProviderProperties providerProperties, TokenRequest tokenRequest) {
        MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
        formData.add(GRANT_TYPE, providerProperties.getAuthorizationGrantType());
        formData.add(CODE, tokenRequest.getCode());
        formData.add(REDIRECT_URI, providerProperties.getRedirectUri());
        formData.add(CLIENT_ID, providerProperties.getClientId());
        formData.add(CLIENT_SECRET, providerProperties.getClientSecret());
        return formData;
    }
}
