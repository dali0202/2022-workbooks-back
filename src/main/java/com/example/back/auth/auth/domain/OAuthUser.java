package com.example.back.auth.auth.domain;

import java.util.Map;

public abstract class OAuthUser {
    public abstract String getId();
    public abstract String getName();
    public abstract String getEmail();
    public abstract String getImageUrl();
}
