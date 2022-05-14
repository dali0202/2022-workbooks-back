package com.example.back.auth.oauth2.exception;

import org.springframework.security.core.AuthenticationException;
// 인증 중 오류 시 발생
public class OAuth2AuthenticationProcessingException extends AuthenticationException {

	public OAuth2AuthenticationProcessingException(String msg, Throwable t) {
		super(msg, t);
	}
	public OAuth2AuthenticationProcessingException(String msg) {
		super(msg);
	}

}