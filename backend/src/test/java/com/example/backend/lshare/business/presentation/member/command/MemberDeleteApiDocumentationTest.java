package com.example.backend.lshare.business.presentation.member.command;

import com.example.backend.lshare.RestdocsController;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("회원 탈퇴 API")
class MemberDeleteApiDocumentationTest extends RestdocsController {

    @Test
    @DisplayName("회원 탈퇴 API Documentation")
    void MEMBER_PROFILE_UPDATE_API() throws Exception {
        mockMvc.perform(delete("/api/members/withdrawal")
                        .headers(getOauthRequestHeaders())
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isNoContent())
                .andDo(document("member/member-withdrawal"));
    }
}
