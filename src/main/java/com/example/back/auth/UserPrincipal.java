package com.example.back.auth;

import com.example.back.user.domain.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Collection;
import java.util.Map;

public class UserPrincipal implements OAuth2User {

	private User user;
	private Map<String, Object> attributes;

	private UserPrincipal(User user) {
		this.user = user;
	}

	public static UserPrincipal create(User user) {
		return new UserPrincipal(user);
	}

	public static UserPrincipal create(User user, Map<String, Object> attributes) {
		UserPrincipal userPrincipal = UserPrincipal.create(user);
		userPrincipal.setAttributes(attributes);
		return userPrincipal;
	}

	public User getUser() { return user; }
	@Override
	public Map<String, Object> getAttributes() {
		return attributes;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return null;
	}

	@Override
	public String getName() { return user.getName(); }

	public String getEmail() { return user.getEmail(); }

	public String getAuthProvider() {
		return String.valueOf(user.getAuthProvider());
	}

	public void setAttributes(Map<String, Object> attributes) { this.attributes = attributes; }
}
