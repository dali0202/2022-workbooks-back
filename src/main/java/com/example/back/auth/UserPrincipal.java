package com.example.back.auth;

import com.example.back.user.domain.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;

public class UserPrincipal implements OAuth2User, UserDetails {

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
		return Collections.singletonList(new SimpleGrantedAuthority(user.getRole().name()));
	}

	@Override
	public String getName() { return user.getName(); }

	public String getEmail() { return user.getEmail(); }

	public String getAuthProvider() {
		return String.valueOf(user.getAuthProvider());
	}

	public void setAttributes(Map<String, Object> attributes) { this.attributes = attributes; }

	@Override
	public String getPassword() {
		return null;
	}
	@Override
	public String getUsername() {
		return user.getName();
	}
	@Override
	public boolean isAccountNonExpired() {
		return false;
	}
	@Override
	public boolean isAccountNonLocked() {
		return false;
	}
	@Override
	public boolean isCredentialsNonExpired() {
		return false;
	}
	@Override
	public boolean isEnabled() {
		return false;
	}
}
