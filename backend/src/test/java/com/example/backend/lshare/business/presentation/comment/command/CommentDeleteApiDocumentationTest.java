package com.example.backend.lshare.business.presentation.comment.command;

import com.example.backend.business.core.comment.entity.Comment;
import com.example.backend.data.comment.CommentTestData;
import com.example.backend.lshare.RestdocsController;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.restdocs.request.ParameterDescriptor;
import org.springframework.restdocs.request.PathParametersSnippet;

import java.util.List;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.delete;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;

@DisplayName("댓글 삭제 API 테스트")
class CommentDeleteApiDocumentationTest extends RestdocsController {

    @Test
    @DisplayName("댓글 삭제 API Documentation")
    void COMMENT_DELETE_API() throws Exception {
        Comment newComment = CommentTestData.createCommentWithId();

        mockMvc.perform(delete("/api/comments/{commentId}", newComment.getCommentId())
                        .headers(getOauthRequestHeaders())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8"))
                .andDo(document("comment/comment-delete",
                        COMMENT_DELETE_PATH_PARAMETER()
                ));
    }

    private static PathParametersSnippet COMMENT_DELETE_PATH_PARAMETER() {
        List<ParameterDescriptor> descriptors = List.of(parameterWithName("commentId").description("수정할 댓글 아이디"));
        return pathParameters(descriptors);
    }
}
