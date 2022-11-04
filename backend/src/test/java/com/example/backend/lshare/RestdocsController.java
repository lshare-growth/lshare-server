package com.example.backend.lshare;

import com.example.backend.business.core.member.entity.Member;
import com.example.backend.business.web.comment.facade.CommentCommandFacade;
import com.example.backend.business.web.comment.facade.CommentQueryFacade;
import com.example.backend.business.web.comment.presentation.CommentCommandController;
import com.example.backend.business.web.member.facade.follow.FollowCommandFacade;
import com.example.backend.business.web.member.facade.member.MemberCommandFacade;
import com.example.backend.business.web.member.facade.member.MemberQueryFacade;
import com.example.backend.business.web.member.presentation.follow.FollowCommandController;
import com.example.backend.business.web.member.presentation.member.MemberQueryController;
import com.example.backend.business.web.reaction.facade.ReactionCommandFacade;
import com.example.backend.business.web.reaction.presentation.ReactionCommandController;
import com.example.backend.business.web.study.application.StudyCommandService;
import com.example.backend.business.web.study.facade.StudyCommandFacade;
import com.example.backend.business.web.study.presentation.StudyCommandController;
import com.example.backend.business.web.study.presentation.StudySupportController;
import com.example.backend.common.log.LogTrace;
import com.example.backend.common.login.model.OauthManager;
import com.example.backend.common.login.model.token.jwt.JwtTokenProvider;
import com.example.backend.common.login.model.token.jwt.JwtTokenValidator;
import com.example.backend.common.mapper.api.EnumMapper;
import com.example.backend.common.security.HTMLCharacterEscapes;
import com.example.backend.data.member.MemberTestData;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.restdocs.headers.HeaderDocumentation;
import org.springframework.restdocs.headers.RequestHeadersSnippet;
import org.springframework.restdocs.headers.ResponseHeadersSnippet;
import org.springframework.restdocs.snippet.Attributes;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import static org.springframework.restdocs.headers.HeaderDocumentation.headerWithName;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
@AutoConfigureRestDocs
public abstract class RestdocsController {

    @Autowired
    protected ObjectMapper objectMapper;

    @Autowired
    protected StudyCommandService studyCommandService;

    @InjectMocks
    protected StudySupportController studySupportController;

    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    protected OauthManager oauthManager;

    @InjectMocks
    protected ReactionCommandController reactionController;

    @MockBean
    protected MemberCommandFacade memberCommandFacade;

    @InjectMocks
    protected FollowCommandController followCommandController;

    @Mock
    protected FollowCommandFacade followCommandFacade;

    @InjectMocks
    protected MemberQueryController memberQueryController;

    @MockBean
    protected MemberQueryFacade memberQueryFacade;

    @MockBean
    protected ReactionCommandFacade reactionCommandFacade;

    @InjectMocks
    protected CommentCommandController commentController;

    @MockBean
    protected CommentCommandFacade commentCommandFacade;

    @MockBean
    protected CommentQueryFacade commentQueryFacade;

    @InjectMocks
    protected StudyCommandController studyCommandController;

    @MockBean
    protected LogTrace logTrace;

    @MockBean
    protected StudyCommandFacade studyFacade;

    @MockBean
    protected EnumMapper enumMapper;

    @Autowired
    protected JwtTokenProvider jwtTokenProvider;

    @Autowired
    protected JwtTokenValidator jwtTokenValidator;

    protected Member member = MemberTestData.createMemberTestDataWithId();
    protected String BEARER = "Bearer ";
    protected String accessToken;
    protected String refreshToken;

    @Autowired
    protected EntityManager entityManager;

    @BeforeEach
    void setUP() {
        objectMapper.getFactory().setCharacterEscapes(new HTMLCharacterEscapes());
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        objectMapper.registerModule(new JavaTimeModule());

        accessToken = jwtTokenProvider.createAccessToken(member, null);
        refreshToken = jwtTokenProvider.createRefreshToken(member, null);
    }

    @AfterEach
    void afterEachTest() {
        entityManager.clear();
    }

    protected RequestHeadersSnippet OauthRequestHeaders() {
        return requestHeaders().and(
                List.of(
                        headerWithName("Authorization").attributes(new Attributes.Attribute("Authorization", Collections.singletonList(BEARER + accessToken))).description("AccessToken"),
                        headerWithName("RefreshToken").attributes(new Attributes.Attribute("RefreshToken", Collections.singletonList(BEARER + refreshToken))).description("RefreshToken")
                ));
    }

    protected ResponseHeadersSnippet getResponseHeaders() {
        return HeaderDocumentation.responseHeaders(
                headerWithName("Content-Type").attributes(new Attributes.Attribute("Content-Type", Collections.singletonList("application/json"))).description("Content-Type"),
                headerWithName("Date").attributes(new Attributes.Attribute("Date", Collections.singletonList(LocalDateTime.now()))).description("날짜"),
                headerWithName("Access-Control-Expose-Headers").attributes(new Attributes.Attribute("Access-Control-Expose-Headers", Collections.singletonList(LocalDateTime.now())))
        );
    }

    protected RequestHeadersSnippet requestHeaders() {
        return HeaderDocumentation.requestHeaders(
                headerWithName("Access-Control-Allow-Origin").attributes(new Attributes.Attribute("Access-Control-Allow-Origin", Collections.singletonList("lnshare.com"))).description("Access-Control-Allow-Origin"),
                headerWithName("Access-Control-Expose-Headers").attributes(new Attributes.Attribute("Access-Control-Expose-Headers", Collections.singletonList("Content-Type, Accept"))).description("Access-Control-Allow-Headers")
        );
    }

    protected HttpHeaders getOauthRequestHeaders() {
        HttpHeaders headers = getCommonHeaders();

        headers.put("Authorization", Collections.singletonList(BEARER + accessToken));
        headers.put("RefreshToken", Collections.singletonList(BEARER + refreshToken));
        return headers;
    }

    protected HttpHeaders getCommonHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.put("Host", Collections.singletonList("lshare"));
        headers.put("Server", Collections.singletonList("lshare"));
        return headers;
    }
}

