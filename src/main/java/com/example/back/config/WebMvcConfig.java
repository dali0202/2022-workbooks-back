package com.example.back.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
//	private final long MAX_AGE_SECS = 3600;
//
//	@Override // CORS설정. Spring Security 사용중이기 때문에 SecurityConfig에도 설정 필요
//	public void addCorsMappings(CorsRegistry registry) {
//		registry
//			.addMapping("/**")
//			.allowedOriginPatterns("*")
//			.allowedMethods("GET", "POST", "PUT", "DELETE")
//			.allowedHeaders("*")
//			.allowCredentials(true)
//			.maxAge(MAX_AGE_SECS);
//	}
}