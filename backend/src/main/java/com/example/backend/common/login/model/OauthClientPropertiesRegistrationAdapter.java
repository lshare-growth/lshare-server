package com.example.backend.common.login.model;

import java.util.HashMap;
import java.util.Map;

public final class OauthClientPropertiesRegistrationAdapter {

    public static Map<String, ClientRegistration> getOauthProviders(final OauthClientProperties properties) {
        final Map<String, ClientRegistration> clientRegistrationMap = new HashMap<>();

        properties.getRegistration().forEach((key, value) ->
                clientRegistrationMap.put(key, ClientRegistration.bind(value, properties.getProvider(key))));
        return clientRegistrationMap;
    }
}
