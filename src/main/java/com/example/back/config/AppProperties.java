package com.example.back.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "app")
public class AppProperties {
	private final Auth auth = new Auth();
	private final Oauth oauth = new Oauth();
	@Getter
	@Setter
	public static class Auth {
		private String tokenSecret;
		private long tokenExpirationTime;
	}
	@Getter
	@Setter
	public static class Oauth {
		private Google google;
		private Kakao kakao;
		private Naver naver;
	}
	@Getter
	@Setter
	public static class Google {
		private String provider;
		private String clientId;
		private String clientSecret;
		private String redirectUri;
		private String tokenUri;
		private String AuthorizationGrantType;
		private String AuthorizationUri;
	}
	@Getter
	@Setter
	public static class Kakao {
		private String provider;
		private String clientId;
		private String clientSecret;
		private String redirectUri;
		private String tokenUri;
		private String AuthorizationGrantType;
		private String AuthorizationUri;
	}
	@Getter
	@Setter
	public static class Naver {
		private String provider;
		private String clientId;
		private String clientSecret;
		private String redirectUri;
		private String tokenUri;
		private String AuthorizationGrantType;
		private String AuthorizationUri;
	}
}
