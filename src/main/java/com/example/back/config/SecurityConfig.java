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
				.cors()
				.and()
				.sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS) // ????????? ???????????? ??????
				.and()
				.csrf().disable() // csrf ????????? - REST API??? stateless ?????? csrf??? ?????? ????????? ????????????.
				.headers().frameOptions().disable()
				.and()
				.formLogin().disable() // ????????? ??? ?????????
				.httpBasic().disable() // Http basic Auth ???????????? ????????? ???????????? ??????(disable ??? ????????? ????????? ??????)
				//.exceptionHandling().authenticationEntryPoint(new RestAuthenticationEntryPoint()) // ??????,????????? ?????? ?????? ?????? ??? ??????
				//.and()
				.authorizeRequests()
				.antMatchers("/auth/**", "/oauth2/**").permitAll() // Security ?????? Url
				.antMatchers(HttpMethod.POST, "/api/workbooks/**")
				.hasRole(USER)
				.anyRequest().permitAll()
				.and()
				.oauth2Login()
				.authorizationEndpoint().baseUri("/oauth2/authorization") // ?????? ????????? Url
				.authorizationRequestRepository(cookieOAuth2AuthorizationRequestRepository()) // ?????? ????????? ????????? ???????????? ??????
				.and()
				.redirectionEndpoint().baseUri("/login/oauth2/code/*") // ?????? ?????? ??? Redirect Url
				.and()
				.userInfoEndpoint().userService(customOAuth2UserService) // ????????? ?????? ????????? ????????? ????????????
				.and()
				.successHandler(oAuth2AuthenticationSuccessHandler) // ?????? ?????? ??? Handler
				.failureHandler(oAuth2AuthenticationFailureHandler); // ?????? ?????? ??? Handler

		http.addFilterBefore(tokenAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
	}
}
