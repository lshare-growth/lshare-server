package com.example.backend.common.login.model;

import java.util.Map;

public final class InMemoryClientRegisterRepository {

    private Map<String, ClientRegistration> clientRegistration;

    public InMemoryClientRegisterRepository() {
    }

    public InMemoryClientRegisterRepository(final Map<String, ClientRegistration> clientRegistration) {
        this.clientRegistration = clientRegistration;
    }

    public Map<String, ClientRegistration> getClientRegistration() {
        return clientRegistration;
    }

    public ClientRegistration findLoginPageByName(final String name) {
        return clientRegistration.get(name);
    }
}
