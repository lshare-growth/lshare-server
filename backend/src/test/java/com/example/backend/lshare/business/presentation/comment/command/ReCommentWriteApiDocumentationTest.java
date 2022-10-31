package com.example.backend.lshare.business.presentation.comment.command;

import com.example.backend.business.core.comment.entity.Comment;
import com.example.backend.business.core.comment.entity.values.CommentContent;
import com.example.backend.business.core.comment.entity.values.CommentParentId;
import com.example.backend.business.core.member.entity.values.MemberId;
import com.example.backend.business.web.comment.presentation.dto.request.CommentReWriteRequest;
import com.example.backend.data.comment.CommentTestData;
import com.example.backend.lshare.RestdocsController;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.FieldDescriptor;
import org.springframework.restdocs.payload.JsonFieldType;

import static com.example.backend.data.comment.CommentTestData.COMMENT_ID;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("댓글 작성 API 테스트")
class ReCommentWriteApiDocumentationTest extends RestdocsController {

    @Test
    @DisplayName("")
    void RE_COMMENT_WRITE_API() throws Exception {
        CommentReWriteRequest request = new CommentReWriteRequest("공유해주신 내용 잘봤습니다. 많은 도움이 되었습니다.");
        Comment response = CommentTestData.createCommentWithId();
        when(commentCommandFacade.writeReComment(any(MemberId.class), any(CommentParentId.class), any(CommentContent.class)))
                .thenReturn(response);

        mockMvc.perform(post("/api/comments/{commentId}/recomments", COMMENT_ID)
                        .headers(getOauthRequestHeaders())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andDo(document("comment/re-comment-write",
                        requestFields(RE_COMMENT_WRITE_REQUEST_FIELDS),
                        responseFields(RE_COMMENT_WRITE_RESPONSE_FIELDS)));
    }

    private final FieldDescriptor[] RE_COMMENT_WRITE_REQUEST_FIELDS = new FieldDescriptor[]{
            fieldWithPath("content").type(JsonFieldType.STRING).description("댓글 내용")
    };

    private final FieldDescriptor[] RE_COMMENT_WRITE_RESPONSE_FIELDS = new FieldDescriptor[]{
            fieldWithPath("commentId").type(JsonFieldType.NUMBER).description("작성된 댓글 아이디")
    };
}
