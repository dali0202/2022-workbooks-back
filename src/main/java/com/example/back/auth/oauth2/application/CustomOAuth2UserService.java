package com.example.back.auth.oauth2.application;

import com.example.back.auth.UserPrincipal;
import com.example.back.auth.oauth2.domain.AuthProvider;
import com.example.back.auth.oauth2.domain.info.OAuth2UserInfo;
import com.example.back.auth.oauth2.domain.info.OAuth2UserInfoFactory;
import com.example.back.auth.oauth2.exception.OAuth2AuthenticationProcessingException;
import com.example.back.user.domain.Role;
import com.example.back.user.domain.User;
import com.example.back.user.domain.UserRepository;
import com.nimbusds.oauth2.sdk.util.StringUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

	private final UserRepository userRepository;

	@Override
	public OAuth2User loadUser(OAuth2UserRequest oAuth2UserRequest) throws OAuth2AuthenticationException {
		OAuth2User oAuth2User = super.loadUser(oAuth2UserRequest);

		try {
			return processOAuth2User(oAuth2UserRequest, oAuth2User);
		} catch (AuthenticationException e) {
			throw e;
		} catch (Exception e) {
			throw new InternalAuthenticationServiceException(e.getMessage(), e.getCause());
		}
	}

	private OAuth2User processOAuth2User(OAuth2UserRequest oAuth2UserRequest, OAuth2User oAuth2User) {
		OAuth2UserInfo oAuth2UserInfo = OAuth2UserInfoFactory.getOAuth2UserInfo(
			oAuth2UserRequest.getClientRegistration().getRegistrationId(),
			oAuth2User.getAttributes()
		);

		if (StringUtils.isBlank(oAuth2UserInfo.getEmail()))
			throw new OAuth2AuthenticationProcessingException("empty email");
		Optional<User> userOptional = userRepository.findByEmail(oAuth2UserInfo.getEmail());
		User user;
		if (userOptional.isPresent()) {
			if (!userOptional.get().getAuthProvider().equals(
				AuthProvider.valueOf(oAuth2UserRequest.getClientRegistration().getRegistrationId())))
				throw new OAuth2AuthenticationProcessingException("already sign up other provider");
			user = updateUser(userOptional.get(), oAuth2UserInfo);
		} else {
			user = registerUser(oAuth2UserRequest, oAuth2UserInfo);
		}
		return UserPrincipal.create(user, oAuth2User.getAttributes());
	}

	private User registerUser(OAuth2UserRequest oAuth2UserRequest, OAuth2UserInfo oAuth2UserInfo) {
		String provider = oAuth2UserRequest.getClientRegistration().getRegistrationId();

		return userRepository.save(User.builder()
			.name(oAuth2UserInfo.getName())
			.email(oAuth2UserInfo.getEmail())
			.imageUrl(oAuth2UserInfo.getImageUrl())
			.role(Role.USER)
			.provider(AuthProvider.valueOf(provider.toLowerCase()))
			.build());
	}

	private User updateUser(User user, OAuth2UserInfo oAuth2UserInfo) {
		user.update(oAuth2UserInfo.getName(), oAuth2UserInfo.getImageUrl());
		return user;
	}
}