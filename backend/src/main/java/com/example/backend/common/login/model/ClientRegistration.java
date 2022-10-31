package com.example.backend.common.login.model;

public final class ClientRegistration {

    private final String clientId;
    private final String clientSecret;
    private final String redirectUri;
    private final String loginPage;

    private final String tokenUri;
    private final String userInfoUri;
    private final String userNameAttribute;
    private final String userAuthrozationUri;

    public ClientRegistration(String clientId,
                              String clientSecret,
                              String redirectUri,
                              String loginPage,
                              String tokenUri,
                              String userInfoUri,
                              String userNameAttribute,
                              String userAuthrozationUri) {

        this.clientId = clientId;
        this.clientSecret = clientSecret;
        this.redirectUri = redirectUri;
        this.loginPage = loginPage;
        this.tokenUri = tokenUri;
        this.userInfoUri = userInfoUri;
        this.userNameAttribute = userNameAttribute;
        this.userAuthrozationUri = userAuthrozationUri;
    }

    public static ClientRegistration bind(OauthClientProperties.Registration registration, OauthClientProperties.Provider provider) {
        return new ClientRegistration(
                registration.getClientId(),
                registration.getClientSecret(),
                registration.getRedirectUri(),
                registration.getLoginPage(),
                provider.getTokenUri(),
                provider.getUserInfoUri(),
                provider.getUserNameAttribute(),
                provider.getUserAuthrozationUri()
        );
    }

    public String getClientId() {
        return clientId;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public String getRedirectUri() {
        return redirectUri;
    }

    public String getLoginPage() {
        return loginPage;
    }

    public String getTokenUri() {
        return tokenUri;
    }

    public String getUserInfoUri() {
        return userInfoUri;
    }

    public String getUserNameAttribute() {
        return userNameAttribute;
    }

    public String getUserAuthrozationUri() {
        return userAuthrozationUri;
    }
}
