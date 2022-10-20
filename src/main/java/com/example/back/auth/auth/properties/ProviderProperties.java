package com.example.back.auth.auth.properties;

public abstract class ProviderProperties {
    public abstract String getProvider();
    public abstract String getClientId();
    public abstract String getClientSecret();
    public abstract String getRedirectUri();
    public abstract String getTokenUri();
    public abstract String getAuthorizationGrantType();
    public abstract String getAuthorizationUri();
}
