package com.example.backend.lshare.business.presentation.comment.command;

import com.example.backend.business.web.comment.presentation.dto.request.CommentUpdateRequest;
import com.example.backend.lshare.RestdocsController;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.FieldDescriptor;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.restdocs.request.ParameterDescriptor;

import static com.example.backend.data.comment.CommentTestData.COMMENT_ID;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.put;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("댓글 수정 API 테스트")
class CommentUpdateApiDocumentationTest extends RestdocsController {

    @Test
    @DisplayName("댓글 수정 API Documentation")
    void COMMENT_UPDATE_API() throws Exception {
        CommentUpdateRequest request = new CommentUpdateRequest("좋은 내용 공유 감사합니다.");

        mockMvc.perform(put("/api/comments/{commentId}", COMMENT_ID)
                        .headers(getOauthRequestHeaders())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andDo(document("comment/comment-update",
                        pathParameters(COMMENT_UPDATE_PATH_PARAMETER()),
                        requestFields(COMMENT_UPDATE_REQUEST_FIELDS)))
                .andDo(print());
    }

    private static ParameterDescriptor COMMENT_UPDATE_PATH_PARAMETER() {
        return parameterWithName("commentId").description("수정할 댓글 아이디");
    }

    private final FieldDescriptor[] COMMENT_UPDATE_REQUEST_FIELDS = new FieldDescriptor[]{
            fieldWithPath("content").type(JsonFieldType.STRING).description("수정할 댓글 내용")
    };
}
