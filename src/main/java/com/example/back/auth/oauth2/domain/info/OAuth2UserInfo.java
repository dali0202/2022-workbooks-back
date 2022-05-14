package com.example.back.auth.oauth2.domain.info;

import java.util.Map;

public abstract class OAuth2UserInfo {
	protected Map<String, Object> attributes;

	public OAuth2UserInfo(Map<String, Object> attributes) { this.attributes = attributes; }

	public Map<String, Object> getAttributes() { return this.attributes; }

	public abstract String getId();

	public abstract String getName();

	public abstract String getEmail();

	public abstract String getImageUrl();
}
