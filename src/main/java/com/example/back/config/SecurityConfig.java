package com.example.back.config;

import com.example.back.auth.TokenAuthenticationFilter;
import com.example.back.auth.oauth2.application.CustomOAuth2UserService;
import com.example.back.auth.oauth2.domain.HttpCookieOAuth2AuthorizationRequestRepository;
import com.example.back.auth.oauth2.handler.OAuth2AuthenticationFailureHandler;
import com.example.back.auth.oauth2.handler.OAuth2AuthenticationSuccessHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.CorsUtils;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@EnableGlobalMethodSecurity(prePostEnabled = true)
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	private static final String USER = "USER";
	private final CustomOAuth2UserService customOAuth2UserService;
	private final OAuth2AuthenticationSuccessHandler oAuth2AuthenticationSuccessHandler;
	private final OAuth2AuthenticationFailureHandler oAuth2AuthenticationFailureHandler;

	@Bean
	public TokenAuthenticationFilter tokenAuthenticationFilter() {
		return new TokenAuthenticationFilter();
	}

	@Bean
	public HttpCookieOAuth2AuthorizationRequestRepository cookieOAuth2AuthorizationRequestRepository() {
		return new HttpCookieOAuth2AuthorizationRequestRepository();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
//				.cors()
//				.and()
				.sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS) // 세션을 사용하지 않음
				.and()
				.csrf().disable() // csrf 미사용 - REST API는 stateless 하여 csrf에 대한 방어가 필요없다.
				.headers().frameOptions().disable()
				.and()
				.formLogin().disable() // 로그인 폼 미사용
				.httpBasic().disable() // Http basic Auth 기반으로 로그인 인증창이 열림(disable 시 인증창 열리지 않음)
				//.exceptionHandling().authenticationEntryPoint(new RestAuthenticationEntryPoint()) // 인증,인가가 되지 않은 요청 시 발생
				//.and()
				.authorizeRequests()
				.antMatchers("/auth/**", "/oauth2/**").permitAll() // Security 허용 Url
				.antMatchers(HttpMethod.POST, "/api/workbooks/**")
				.hasRole(USER)

				.anyRequest().permitAll()
				.and()
				.oauth2Login()
				.authorizationEndpoint().baseUri("/oauth2/authorization") // 소셜 로그인 Url
				.authorizationRequestRepository(cookieOAuth2AuthorizationRequestRepository()) // 인증 요청을 쿠키에 저장하고 검색
				.and()
				.redirectionEndpoint().baseUri("/login/oauth2/code/*") // 소셜 인증 후 Redirect Url
				.and()
				.userInfoEndpoint().userService(customOAuth2UserService) // 소셜의 회원 정보를 받아와 가공처리
				.and()
				.successHandler(oAuth2AuthenticationSuccessHandler) // 인증 성공 시 Handler
				.failureHandler(oAuth2AuthenticationFailureHandler); // 인증 실패 시 Handler

		http.addFilterBefore(tokenAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
	}
}
