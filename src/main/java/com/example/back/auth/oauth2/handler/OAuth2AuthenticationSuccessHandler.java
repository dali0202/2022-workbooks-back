package com.example.back.auth.oauth2.handler;


import com.example.back.auth.oauth2.domain.HttpCookieOAuth2AuthorizationRequestRepository;
import com.example.back.config.AppProperties;
import com.example.back.exception.AuthException;
import com.example.back.exception.ErrorCode;
import com.example.back.util.CookieUtils;
import com.example.back.util.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URI;
import java.util.Optional;

import static com.example.back.auth.oauth2.domain.HttpCookieOAuth2AuthorizationRequestRepository.REDIRECT_URI_PARAM_COOKIE_NAME;


@Component
@RequiredArgsConstructor
public class OAuth2AuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
	private final JwtUtils jwtUtils;
	private final AppProperties appProperties;
	private final HttpCookieOAuth2AuthorizationRequestRepository httpCookieOAuth2AuthorizationRequestRepository;

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws
		IOException {
		String targetUrl = determineTargetUrl(request, response, authentication);
		if (response.isCommitted()) {
			return;
		}
		clearAuthenticationAttributes(request, response);
		getRedirectStrategy().sendRedirect(request, response, targetUrl);
	}
	protected String determineTargetUrl(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
		Optional<String> redirectUri = CookieUtils.getCookie(request, REDIRECT_URI_PARAM_COOKIE_NAME).map(Cookie::getValue);

		if (redirectUri.isPresent() && !isAuthorizedRedirectUri(redirectUri.get()))
			throw new AuthException(ErrorCode.UNAUTHORIZED_REDIRECT_URI);

		String targetUri = redirectUri.orElse(getDefaultTargetUrl());

		String token = jwtUtils.createToken(authentication);

		return UriComponentsBuilder.fromUriString(targetUri)
			.queryParam("token", token)
			.build().toUriString();
	}

	protected void clearAuthenticationAttributes(HttpServletRequest request, HttpServletResponse response) {
		super.clearAuthenticationAttributes(request);
		httpCookieOAuth2AuthorizationRequestRepository.removeAuthorizationRequestCookies(request, response);
	}

	private boolean isAuthorizedRedirectUri(String uri) {
		URI clientRedirectUri = URI.create(uri);
		return appProperties.getOauth2().getAuthorizedRedirectUris()
			.stream()
			.anyMatch(authorizedRedirectUri -> {
				URI authorizedURI = URI.create(authorizedRedirectUri);
				if (authorizedURI.getHost().equalsIgnoreCase(clientRedirectUri.getHost()) && authorizedURI.getPort() == clientRedirectUri.getPort()) {
					return true;
				}
				return false;
			});
	}
}