package com.example.backend.lshare.business.presentation.reaction.command;

import com.example.backend.business.web.reaction.presentation.dto.request.ReactionUpdateRequest;
import com.example.backend.lshare.RestdocsController;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.FieldDescriptor;
import org.springframework.restdocs.payload.JsonFieldType;

import static com.example.backend.data.comment.CommentTestData.COMMENT_ID;
import static com.example.backend.data.study.StudyTestData.STUDY_ID;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("이모티콘 업데이트 API 테스트")
class CommentReactionUpdateApiDocumentationTest extends RestdocsController {

    @Test
    @DisplayName("이모티콘 업데이트 API Documentation")
    void COMMENT_REACTION_UPDATE_API() throws Exception {
        ReactionUpdateRequest request = new ReactionUpdateRequest("❤️");

        mockMvc.perform(post("/api/reactions/{studyId}/comments/{commentId}/reactions", STUDY_ID, COMMENT_ID)
                        .headers(getOauthRequestHeaders())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andDo(document("reaction/reaction-update",
                        requestFields(REACTION_UPDATE_REQUEST_FIELDS)));
    }

    private final FieldDescriptor[] REACTION_UPDATE_REQUEST_FIELDS = new FieldDescriptor[]{
            fieldWithPath("emotion").type(JsonFieldType.STRING).description("추가/삭제할 이모티콘")
    };
}
