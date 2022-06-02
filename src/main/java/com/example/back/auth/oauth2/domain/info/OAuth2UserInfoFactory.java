package com.example.back.auth.oauth2.domain.info;



import com.example.back.auth.oauth2.domain.AuthProvider;
import com.example.back.auth.oauth2.domain.info.impl.GoogleOAuth2UserInfo;
import com.example.back.auth.oauth2.domain.info.impl.KakaoOAuth2UserInfo;
import com.example.back.auth.oauth2.domain.info.impl.NaverOAuth2UserInfo;
import com.example.back.auth.oauth2.exception.OAuth2AuthenticationProcessingException;
import com.example.back.exception.AuthException;
import com.example.back.exception.ErrorCode;

import java.util.Map;

public class OAuth2UserInfoFactory {
	public OAuth2UserInfoFactory() {
	}

	public static OAuth2UserInfo getOAuth2UserInfo(String registrationId, Map<String, Object> attributes) {
		if (registrationId.equalsIgnoreCase(AuthProvider.google.toString())) {
			return new GoogleOAuth2UserInfo(attributes);
		} else if (registrationId.equalsIgnoreCase(AuthProvider.kakao.toString())) {
			return new KakaoOAuth2UserInfo(attributes);
		} else if (registrationId.equalsIgnoreCase(AuthProvider.naver.toString())) {
			return new NaverOAuth2UserInfo(attributes);
		}
		throw new AuthException(ErrorCode.AUTH_ERROR);
	}
}
