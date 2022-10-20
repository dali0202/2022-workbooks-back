package com.example.back.config;

import com.example.back.auth.auth.api.CurrentUserArgumentResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;
@RequiredArgsConstructor
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
	private final long MAX_AGE_SECS = 86400;
	private final CurrentUserArgumentResolver currentUserArgumentResolver;

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry
			.addMapping("/**")
			.allowedOriginPatterns("https://workbooks.ga", "https://www.workbooks.ga", "http://localhost:3000")
			.allowedMethods("*")
			.maxAge(MAX_AGE_SECS);
	}
	@Override
	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
		argumentResolvers.add(currentUserArgumentResolver);
	}
}