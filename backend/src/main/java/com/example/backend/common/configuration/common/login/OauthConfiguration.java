package com.example.backend.common.configuration.common.login;

import com.example.backend.common.login.model.ClientRegistration;
import com.example.backend.common.login.model.InMemoryClientRegisterRepository;
import com.example.backend.common.login.model.OauthClientProperties;
import com.example.backend.common.login.model.OauthClientPropertiesRegistrationAdapter;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
@EnableConfigurationProperties(OauthClientProperties.class)
public class OauthConfiguration {

    private final OauthClientProperties oauthClientProperties;

    public OauthConfiguration(OauthClientProperties oauthClientProperties) {
        this.oauthClientProperties = oauthClientProperties;
    }

    @Bean
    public InMemoryClientRegisterRepository inMemoryClientRegisterRepository() {
        final Map<String, ClientRegistration> providers = OauthClientPropertiesRegistrationAdapter.getOauthProviders(oauthClientProperties);
        return new InMemoryClientRegisterRepository(providers);
    }
}
