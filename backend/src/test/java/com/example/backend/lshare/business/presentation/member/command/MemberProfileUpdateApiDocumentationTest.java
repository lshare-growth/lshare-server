package com.example.backend.lshare.business.presentation.member.command;

import com.example.backend.business.web.member.presentation.member.dto.request.MemberProfileUpdateRequest;
import com.example.backend.lshare.RestdocsController;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.FieldDescriptor;
import org.springframework.restdocs.payload.JsonFieldType;

import java.time.LocalDate;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.put;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("회원 프로필 수정 API 테스트")
class MemberProfileUpdateApiDocumentationTest extends RestdocsController {

    @Test
    @DisplayName("회원 프로필 수정 API Documentation")
    void MEMBER_FOLLOWING_API() throws Exception {
        MemberProfileUpdateRequest request = new MemberProfileUpdateRequest(
                1L,
                "www.tistory.com",
                "BUSAN",
                LocalDate.of(1995, 10, 10),
                "저번주에 가입했는데 이제 인사드립니다. 잘부탁드립니다.");

        mockMvc.perform(put("/api/members/my-profile")
                        .headers(getOauthRequestHeaders())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andDo(document("member/member-profile-update",
                        requestFields(COMMENT_UPDATE_REQUEST_FIELDS)
                ));
    }

    private final FieldDescriptor[] COMMENT_UPDATE_REQUEST_FIELDS = new FieldDescriptor[]{
            fieldWithPath("memberId").type(JsonFieldType.NUMBER).description("수정할 회원 아이디"),
            fieldWithPath("blogUrl").type(JsonFieldType.STRING).description("수정할 블로그 주소").optional(),
            fieldWithPath("district").type(JsonFieldType.STRING).description("수정할 지역").optional(),
            fieldWithPath("birthDate").type(JsonFieldType.STRING).description("수정할 생년월일").optional(),
            fieldWithPath("introduction").type(JsonFieldType.STRING).description("수정할 자기소개").optional()
    };
}
