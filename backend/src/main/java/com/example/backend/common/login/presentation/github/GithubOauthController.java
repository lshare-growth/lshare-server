package com.example.backend.common.login.presentation.github;

import com.example.backend.business.core.member.entity.values.CurrentLoginIpAddress;
import com.example.backend.common.configuration.business.AESUtil;
import com.example.backend.common.login.facade.GithubOauthFacade;
import com.example.backend.common.login.model.ClientRegistration;
import com.example.backend.common.login.model.InMemoryClientRegisterRepository;
import com.example.backend.common.login.model.token.OauthClient;
import com.example.backend.common.login.model.token.WebToken;
import com.example.backend.common.login.model.token.WebTokenProvider;
import com.example.backend.common.login.presentation.OauthController;
import com.example.backend.common.login.presentation.dto.response.AccessTokenResponse;
import com.example.backend.common.login.presentation.dto.response.LoginResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

import static com.example.backend.common.login.model.token.github.GithubToken.GITHUB;

@Slf4j
@RestController
@RequestMapping("/api/oauth")
@RequiredArgsConstructor
public final class GithubOauthController extends OauthController {

    private final InMemoryClientRegisterRepository inMemoryClientRegisterRepository;
    private final WebTokenProvider webTokenProvider;
    private final GithubOauthFacade githubOauthFacade;
    private final AESUtil aesUtil;

    @GetMapping("/login")
    public ResponseEntity<String> loginHome() {

        String loginHomeURL = inMemoryClientRegisterRepository.findLoginPageByName(GITHUB).getLoginPage();

        return ResponseEntity.status(HttpStatus.OK).body(loginHomeURL);
    }

    @Override
    @GetMapping("/callback/github")
    protected ResponseEntity<AccessTokenResponse> login(@RequestParam("code") String code,
                                                        HttpServletRequest httpServletRequest) {

        CurrentLoginIpAddress ipAddress = extractIpAddress(httpServletRequest);
        ClientRegistration clientRegistration = inMemoryClientRegisterRepository.findLoginPageByName(GITHUB);

        WebToken webToken = webTokenProvider.createToken(code, clientRegistration);
        OauthClient oauthClient = webTokenProvider.createOauthClient(webToken.getAccessToken(), clientRegistration);
        LoginResponse loginResponse = githubOauthFacade.save(oauthClient, ipAddress);

        return ResponseEntity.status(HttpStatus.OK)
                .headers(setHeader(loginResponse.getMember(), loginResponse.getJwtToken(), loginResponse.getNotificationRead()))
                .body(AccessTokenResponse.of(loginResponse.getAccessToken()));
    }

    private CurrentLoginIpAddress extractIpAddress(HttpServletRequest httpServletRequest) {
        String ipAddress = (String) httpServletRequest.getAttribute("ipAddress");
        return CurrentLoginIpAddress.from(
                aesUtil.encrypt(ipAddress)
        );
    }
}
