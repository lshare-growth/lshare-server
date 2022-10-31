package com.example.backend.common.login.model.token.github;

import com.example.backend.common.login.model.token.OauthClient;
import com.fasterxml.jackson.annotation.JsonProperty;

public class GithubClient implements OauthClient {

    @JsonProperty("login")
    private String githubId;

    @JsonProperty("avatar_url")
    private String profileImage;

    public GithubClient() {
    }

    public GithubClient(final String githubId, final String profileImage) {
        this.githubId = githubId;
        this.profileImage = profileImage;
    }

    @Override
    public String getClientId() {
        return githubId;
    }

    @Override
    public String getProfileImage() {
        return profileImage;
    }

    @Override
    public Boolean getMessageCheckStatus() {
        return Boolean.FALSE;
    }

    @Override
    public String toString() {
        return githubId;
    }
}
