package com.example.backend.common.login.model.token.github;

import com.example.backend.common.login.model.token.WebToken;
import com.fasterxml.jackson.annotation.JsonProperty;

public class GithubToken implements WebToken {

    public static final String GITHUB = "github";
    private static final String TOKEN_DELIMETER = " ";

    @JsonProperty(value = "access_token")
    private String accessToken;

    @JsonProperty(value = "token_type")
    private String tokenType;

    @JsonProperty("scope")
    private String scope;

    @Override
    public String getAccessToken() {
        return tokenType + TOKEN_DELIMETER + accessToken;
    }

    public String getTokenType() {
        return tokenType;
    }

    @Override
    public String toString() {
        return accessToken;
    }
}
