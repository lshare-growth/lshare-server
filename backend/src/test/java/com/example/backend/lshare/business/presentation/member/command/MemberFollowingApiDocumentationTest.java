package com.example.backend.lshare.business.presentation.member.command;

import com.example.backend.business.web.member.presentation.follow.dto.request.FollowRequest;
import com.example.backend.lshare.RestdocsController;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.FieldDescriptor;
import org.springframework.restdocs.payload.JsonFieldType;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;

@DisplayName("회원 팔로잉 API")
class MemberFollowingApiDocumentationTest extends RestdocsController {

    @Test
    @DisplayName("회원 팔로잉 API Documentation")
    void MEMBER_FOLLOWING_API() throws Exception {
        FollowRequest request = new FollowRequest(2L);

        mockMvc.perform(post("/api/members/following")
                        .headers(getOauthRequestHeaders())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andDo(document("member/member-follow",
                        requestFields(MEMBER_FOLLOWING_API_REQUEST_FIELDS)
                ));
    }

    private final FieldDescriptor[] MEMBER_FOLLOWING_API_REQUEST_FIELDS = new FieldDescriptor[]{
            fieldWithPath("targetId").type(JsonFieldType.NUMBER).description("팔로우 할 대상 아이디")
    };
}
