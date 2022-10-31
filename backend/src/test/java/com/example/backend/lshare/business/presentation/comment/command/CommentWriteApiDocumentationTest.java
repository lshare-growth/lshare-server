package com.example.backend.lshare.business.presentation.comment.command;

import com.example.backend.business.core.comment.entity.Comment;
import com.example.backend.business.core.comment.entity.values.CommentContent;
import com.example.backend.business.core.member.entity.values.MemberId;
import com.example.backend.business.core.study.entity.values.StudyId;
import com.example.backend.business.web.comment.presentation.dto.request.CommentWriteRequest;
import com.example.backend.data.comment.CommentTestData;
import com.example.backend.lshare.RestdocsController;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.FieldDescriptor;
import org.springframework.restdocs.payload.JsonFieldType;

import static com.example.backend.data.study.StudyTestData.STUDY_ID;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("댓글 작성 API 테스트")
class CommentWriteApiDocumentationTest extends RestdocsController {

    @Test
    @DisplayName("댓글 작성 API Documentation")
    void COMMENT_WRITE_API() throws Exception {
        CommentWriteRequest request = new CommentWriteRequest(STUDY_ID, "열심히 스터디 참여하겠습니다.");
        Comment response = CommentTestData.createCommentWithId();

        when(commentCommandFacade.writeComment(any(MemberId.class), any(StudyId.class), any(CommentContent.class)))
                .thenReturn(response);

        mockMvc.perform(post("/api/comments")
                        .headers(getOauthRequestHeaders())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andDo(document("comment/comment-write",
                        requestFields(COMMENT_WRITE_REQUEST_FIELDS),
                        responseFields(COMMENT_WRITE_RESPONSE_FIELDS)));
    }

    private final FieldDescriptor[] COMMENT_WRITE_REQUEST_FIELDS = new FieldDescriptor[]{
            fieldWithPath("studyId").type(JsonFieldType.NUMBER).description("댓글을 작성할 스터디 아이디"),
            fieldWithPath("content").type(JsonFieldType.STRING).description("작성할 댓글 내용")
    };

    private static final FieldDescriptor[] COMMENT_WRITE_RESPONSE_FIELDS = new FieldDescriptor[]{
            fieldWithPath("commentId").type(JsonFieldType.NUMBER).description("작성된 댓글 아이디")
    };
}
