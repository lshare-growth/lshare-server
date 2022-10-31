package com.example.backend.common.login.model;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

import java.util.HashMap;
import java.util.Map;

@ConstructorBinding
@ConfigurationProperties(prefix = "oauth.client")
public final class OauthClientProperties {

    private final Map<String, Registration> registration = new HashMap<>();
    private final Map<String, Provider> provider = new HashMap<>();

    public OauthClientProperties() {
    }

    public Map<String, Registration> getRegistration() {
        return registration;
    }

    public Map<String, Provider> getProvider() {
        return provider;
    }

    public Provider getProvider(final String key) {
        return provider.get(key);
    }

    public static class Registration {
        private final String clientId;
        private final String clientSecret;
        private final String redirectUri;
        private final String loginPage;

        public Registration(final String clientId, final String clientSecret, final String redirectUri, final String loginPage) {
            this.clientId = clientId;
            this.clientSecret = clientSecret;
            this.redirectUri = redirectUri;
            this.loginPage = loginPage;
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
    }

    public static class Provider {
        private final String tokenUri;
        private final String userInfoUri;
        private final String userNameAttribute;
        private final String userAuthrozationUri;

        public Provider(final String tokenUri, final String userInfoUri, final String userAuthrozationUri, final String userNameAttribute) {
            this.tokenUri = tokenUri;
            this.userInfoUri = userInfoUri;
            this.userAuthrozationUri = userAuthrozationUri;
            this.userNameAttribute = userNameAttribute;
        }

        public String getTokenUri() {
            return tokenUri;
        }

        public String getUserInfoUri() {
            return userInfoUri;
        }

        public String getUserAuthrozationUri() {
            return userAuthrozationUri;
        }

        public String getUserNameAttribute() {
            return userNameAttribute;
        }
    }
}
