package com.example.backend.common.login.presentation.dto.response;

public class AccessTokenResponse {

    private final String accessToken;

    public AccessTokenResponse(String accessToken) {
        this.accessToken = accessToken;
    }

    public static AccessTokenResponse of(String accessToken) {
        return new AccessTokenResponse(accessToken);
    }

    public String getAccessToken() {
        return accessToken;
    }

    @Override
    public String toString() {
        return String.format("AccessToken: %s", accessToken);
    }
}
