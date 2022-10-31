package com.example.backend.common.login.model.token.jwt;

public class JwtToken {

    private final String accessToken;
    private final String refreshToken;

    private JwtToken(String accessToken, String refreshToken) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }

    public static JwtToken from(String accessToken, String refreshToken) {
        return new JwtToken(accessToken, refreshToken);
    }

    public String getAccessToken() {
        return accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    @Override
    public String toString() {
        return String.format("AccessToken: %s, RefreshToken: %s", accessToken, refreshToken);
    }
}
