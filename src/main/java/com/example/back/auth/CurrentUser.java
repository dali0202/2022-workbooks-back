package com.example.back.auth;

import org.springframework.security.core.annotation.AuthenticationPrincipal;

@AuthenticationPrincipal(expression = "#this == 'anonymousUser' ? null : user")
public @interface CurrentUser {
}
