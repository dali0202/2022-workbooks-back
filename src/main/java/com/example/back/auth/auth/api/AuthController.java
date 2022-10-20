package com.example.back.auth.auth.api;

import com.example.back.auth.auth.dto.OAuthUriResponse;
import com.example.back.auth.auth.dto.TokenRequest;
import com.example.back.auth.auth.dto.TokenResponse;
import com.example.back.auth.auth.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService authService;

    @GetMapping("/{provider}")
    public ResponseEntity<OAuthUriResponse> requestOAuthUri(@PathVariable String provider) {
        OAuthUriResponse oAuthUriResponse = authService.getOAuthUri(provider);
        return ResponseEntity.ok(oAuthUriResponse);
    }
    @PostMapping("/{provider}/token")
    public ResponseEntity<TokenResponse> requestToken(@PathVariable String provider, @RequestBody TokenRequest tokenRequest) {
        TokenResponse tokenResponse = authService.generateToken(provider, tokenRequest);
        return ResponseEntity.ok(tokenResponse);
    }
}
