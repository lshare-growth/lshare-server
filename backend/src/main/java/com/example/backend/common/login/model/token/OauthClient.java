package com.example.backend.common.login.model.token;

public interface OauthClient {

    String getClientId();

    String getProfileImage();

    Boolean getMessageCheckStatus();
}
