package com.example.backend.lshare.business.presentation.comment.command;

import com.example.backend.business.web.comment.presentation.dto.request.ReCommentDeleteRequest;
import com.example.backend.lshare.RestdocsController;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.restdocs.request.PathParametersSnippet;

import static com.example.backend.data.comment.CommentTestData.COMMENT_ID;
import static com.example.backend.data.comment.CommentTestData.COMMENT_PARENT_ID;
import static com.example.backend.data.study.StudyTestData.STUDY_ID;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.delete;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("댓글 삭제 API 테스트")
class ReCommentDeleteApiDocumentationTest extends RestdocsController {

    @Test
    @DisplayName("대댓글 삭제 API Documentation")
    void RE_COMMENT_DELETE_API() throws Exception {
        ReCommentDeleteRequest request = new ReCommentDeleteRequest(STUDY_ID);

        mockMvc.perform(delete("/api/comments/{commentParentId}/recomments/{commentId}", COMMENT_ID, COMMENT_PARENT_ID)
                        .headers(getOauthRequestHeaders())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andDo(print())
                .andDo(document("comment/recomment-delete",
                        RE_COMMENT_DELETE_PATH_PARAMETERS())
                )
                .andExpect(status().isNoContent());
    }

    private static PathParametersSnippet RE_COMMENT_DELETE_PATH_PARAMETERS() {
        return pathParameters(parameterWithName("commentParentId").description("삭제할 대댓글 부모 아이디"))
                .and(parameterWithName("commentId").description("삭제할 대댓글 아이디"));
    }
}
