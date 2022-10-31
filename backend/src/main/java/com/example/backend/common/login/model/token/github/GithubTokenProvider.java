package com.example.backend.common.login.model.token.github;

import com.example.backend.common.login.model.ClientRegistration;
import com.example.backend.common.login.model.token.OauthClient;
import com.example.backend.common.login.model.token.WebToken;
import com.example.backend.common.login.model.token.WebTokenProvider;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import static org.springframework.http.HttpHeaders.ACCEPT;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

// TODO. WebClient와 RestTemplate의 차이점 학습
@Component
public class GithubTokenProvider extends WebTokenProvider {

    @Override
    public WebToken createToken(String code, ClientRegistration clientRegistration) {
        Assert.notNull(code, "code가 존재하지 않습니다.");
        Assert.notNull(clientRegistration, "clientRegistration이 존재하지 않습니다.");

        final HttpEntity<?> accessTokenRequest = createRequestBody(code, clientRegistration);

        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setRequestFactory(new HttpComponentsClientHttpRequestFactory());

        return restTemplate
                .exchange(clientRegistration.getTokenUri(), HttpMethod.POST, accessTokenRequest, GithubToken.class)
                .getBody();
    }


    @Override
    public HttpEntity<?> createRequestBody(String code, ClientRegistration clientRegistration) {
        final MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        headers.set(ACCEPT, APPLICATION_JSON_VALUE);

        final LinkedMultiValueMap<String, String> payLoad = new LinkedMultiValueMap<>();
        payLoad.set(clientId, clientRegistration.getClientId());
        payLoad.set(clientSecret, clientRegistration.getClientSecret());
        payLoad.set(this.code, code);
        payLoad.set(redirect_url, clientRegistration.getRedirectUri());

        return new HttpEntity<>(payLoad, headers);
    }

    @Override
    public OauthClient createOauthClient(String accessToken, ClientRegistration clientRegistration) {
        Assert.notNull(accessToken, "accessToken이 존재하지 않습니다.");

        MultiValueMap<String, String> form = new LinkedMultiValueMap<>();
        form.add(AUTHORIZATION, accessToken);

        return new RestTemplate().exchange(
                clientRegistration.getUserInfoUri(), GET, new HttpEntity<>(form),
                GithubClient.class).getBody();
    }
}
