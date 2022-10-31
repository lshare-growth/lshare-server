package com.example.backend.common.configuration.common.login;

import com.example.backend.common.api.path.ApiPath;
import com.example.backend.common.api.path.LoginPath;
import com.example.backend.common.api.path.NoticePath;
import com.example.backend.common.api.path.PagePath;
import com.example.backend.common.configuration.common.page.CursoPagerArgumentResolver;
import com.example.backend.common.login.filter.ApiAuthPathInterceptor;
import com.example.backend.common.login.filter.LoginPageAccessInterceptor;
import com.example.backend.common.login.filter.OauthInterceptor;
import com.example.backend.common.login.filter.PageIntercetptor;
import com.example.backend.common.login.model.OauthManager;
import com.example.backend.common.login.model.token.jwt.JwtTokenProvider;
import com.example.backend.common.login.model.token.jwt.JwtTokenValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer {

    private final JwtTokenProvider jwtTokenProvider;
    private final JwtTokenValidator jwtTokenValidator;
    private final OauthManager oauthManager;

    @Autowired
    private CursoPagerArgumentResolver cursoPagerArgumentResolver;

    @Autowired
    private AuthorizationArgumentResolver authorizationArgumentResolver;

    public WebMvcConfiguration(JwtTokenProvider jwtTokenProvider,
                               JwtTokenValidator jwtTokenValidator,
                               OauthManager oauthManager) {
        this.jwtTokenProvider = jwtTokenProvider;
        this.jwtTokenValidator = jwtTokenValidator;
        this.oauthManager = oauthManager;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new ApiAuthPathInterceptor(jwtTokenProvider, jwtTokenValidator, oauthManager))
                .addPathPatterns(NoticePath.getLoginPathURLs());

        registry.addInterceptor(new LoginPageAccessInterceptor(jwtTokenProvider, jwtTokenValidator, oauthManager))
                .addPathPatterns(LoginPath.getLoginPathURLs());

        registry.addInterceptor(new PageIntercetptor(jwtTokenValidator, oauthManager))
                .addPathPatterns(PagePath.getPageURLs());

        registry.addInterceptor(new OauthInterceptor(jwtTokenProvider, jwtTokenValidator, oauthManager))
                .addPathPatterns(ApiPath.getApiURLs());
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(cursoPagerArgumentResolver);
        resolvers.add(authorizationArgumentResolver);
    }
}
