package com.example.back.auth.auth.service;

import com.example.back.auth.auth.domain.AuthProvider;
import com.example.back.auth.auth.domain.LoginUser;
import com.example.back.auth.auth.domain.OAuthUser;
import com.example.back.auth.auth.dto.*;
import com.example.back.auth.auth.properties.ProviderProperties;
import com.example.back.auth.auth.properties.ProviderPropertiesFactory;
import com.example.back.exception.EntityNotFoundException;
import com.example.back.exception.ErrorCode;
import com.example.back.user.domain.Role;
import com.example.back.user.domain.User;
import com.example.back.user.domain.UserRepository;
import com.example.back.util.JwtUtils;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor(access = AccessLevel.PUBLIC)
@Service
public class AuthService {

    private final ProviderPropertiesFactory providerPropertiesFactory;
    private final JwtUtils jwtUtils;
    private final OAuthService oAuthService;
    private final UserRepository userRepository;

    public TokenResponse generateToken(String provider, TokenRequest tokenRequest) {
        ProviderProperties providerProperties = providerPropertiesFactory.getProviderProperties(provider);
        OAuthUser oAuthUser = oAuthService.getOAuthUser(providerProperties, tokenRequest);
        User user = logIn(oAuthUser, provider);
        return TokenResponse.builder()
                .accessToken(jwtUtils.createToken(user.getId(), user.getName(), user.getEmail()))
                .build();
    }

    public User logIn(OAuthUser oAuthUser, String provider) {
        if (userRepository.existsByEmail(oAuthUser.getEmail())) {
            return findByEmail(oAuthUser.getEmail());
        } else {
            return register(oAuthUser, provider);
        }
    }

    public OAuthUriResponse getOAuthUri(String provider) {
        return OAuthUriResponse.
                builder().
                uri(providerPropertiesFactory.getProviderProperties(provider).getAuthorizationUri())
                .build();
    }

    public LoginUser getLoginUser(String accessToken) {
        jwtUtils.validateToken(accessToken);
        Long id = Long.parseLong(jwtUtils.getSubject(accessToken));
        return LoginUser.builder()
                .id(id)
                .build();
    }

    private User register(OAuthUser oAuthUser, String provider) {
        return userRepository.save(User.builder()
                .name(oAuthUser.getName())
                .email(oAuthUser.getEmail())
                .imageUrl(oAuthUser.getImageUrl())
                .role(Role.ROLE_USER)
                .provider(AuthProvider.valueOf(provider.toLowerCase()))
                .build());
    }

    private User findByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException(ErrorCode.USER_NOT_FOUND));
    }
}
