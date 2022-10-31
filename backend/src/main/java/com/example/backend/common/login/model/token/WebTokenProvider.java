package com.example.backend.common.login.model.token;

import com.example.backend.common.login.model.ClientRegistration;
import org.springframework.http.HttpEntity;

public abstract class WebTokenProvider {

    protected final String clientId = "client_id";
    protected final String code = "code";
    protected final String redirect_url = "redirect_url";
    protected final String accessToken = "access_token";
    protected final String clientSecret = "client_secret";

    public abstract WebToken createToken(String code, ClientRegistration clientRegistration);

    public abstract HttpEntity<?> createRequestBody(String code, ClientRegistration clientRegistration);

    public abstract OauthClient createOauthClient(String accessToken, ClientRegistration clientRegistration);
}
