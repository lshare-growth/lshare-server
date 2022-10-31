package com.example.backend.lshare.business.presentation.comment.command;

import com.example.backend.business.web.comment.presentation.dto.request.ReCommentUpdateRequest;
import com.example.backend.lshare.RestdocsController;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.FieldDescriptor;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.restdocs.request.PathParametersSnippet;

import static com.example.backend.data.comment.CommentTestData.COMMENT_ID;
import static com.example.backend.data.comment.CommentTestData.COMMENT_PARENT_ID;
import static com.example.backend.data.study.StudyTestData.STUDY_ID;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.put;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("댓글 수정 API 테스트")
class ReCommentUpdateApiDocumentationTest extends RestdocsController {

    @Test
    @DisplayName("")
    void RE_COMMENT_UPDATE_API() throws Exception {
        ReCommentUpdateRequest request = new ReCommentUpdateRequest(STUDY_ID, "JPA 추가 쿼리는 다른 부분에서 나는 문제입니다. 다시 한 번 확인후 부탁드려요.");

        mockMvc.perform(put("/api/comments/{commentParentId}/recomments/{commentId}", COMMENT_ID, COMMENT_PARENT_ID)
                        .headers(getOauthRequestHeaders())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andDo(document("comment/recomment-update",
                        RE_COMMENT_UPDATE_PATH_PARAMETERS(),
                        requestFields(RE_COMMENT_UPDATE_REQUEST_FIELDS)))
                .andDo(print());
    }

    private static PathParametersSnippet RE_COMMENT_UPDATE_PATH_PARAMETERS() {
        return pathParameters(parameterWithName("commentParentId").description("수정할 대댓글 부모 아이디"))
                .and(parameterWithName("commentId").description("수정할 대댓글 아이디"));
    }

    private final FieldDescriptor[] RE_COMMENT_UPDATE_REQUEST_FIELDS = new FieldDescriptor[]{
            fieldWithPath("studyId").type(JsonFieldType.NUMBER).description("수정할 대댓글 스터디 아이디"),
            fieldWithPath("content").type(JsonFieldType.STRING).description("수정할 대댓글 내용")
    };
}
