package com.example.back.auth.auth.domain.impl;

import com.example.back.auth.auth.domain.OAuthUser;

import java.util.Map;

public class GoogleOAuthUser extends OAuthUser {
    private Map<String, Object> attributes;
    public GoogleOAuthUser(Map<String, Object> attributes) {
        this.attributes = attributes;
    }

    @Override
    public String getId() {
        return (String)  attributes.get("sub");
    }

    @Override
    public String getName() {
        return (String)  attributes.get("name");
    }

    @Override
    public String getEmail() {
        return (String)  attributes.get("email");
    }

    @Override
    public String getImageUrl() {
        return (String)  attributes.get("picture");
    }
}
