package com.example.backend.lshare.business.presentation.reaction.query;

import com.example.backend.data.common.MapperTestData;
import com.example.backend.lshare.RestdocsController;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.restdocs.payload.FieldDescriptor;
import org.springframework.restdocs.payload.JsonFieldType;

import static com.example.backend.lshare.common.RestDocsConfiguration.field;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("이모티콘 목록조회 API 테스트")
class CommentReactionsListApiDocumentationTest extends RestdocsController {

    @Test
    @DisplayName("이모티콘 목록조회 API Documentation")
    void REACTIONS_GET_API() throws Exception {

        when(enumMapper.getValues(anyString())).thenReturn(MapperTestData.getCommentReactions());

        mockMvc.perform(get("/api/reactions/comment-reactions")
                        .headers(getCommonHeaders())
                        .contentType(APPLICATION_JSON)
                        .accept(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(document("reaction/reaction-list",
                        responseFields(REACTION_RESPONSE_FIELDS)));
    }

    private final FieldDescriptor[] REACTION_RESPONSE_FIELDS = new FieldDescriptor[]{
            fieldWithPath("reactions.[].id").type(JsonFieldType.NUMBER).description("댓글 이모티콘 아이디").attributes(field("constraint", "Not Null")),
            fieldWithPath("reactions.[].type").type(JsonFieldType.STRING).description("댓글 이모티콘 타입").attributes(field("constraint", "지정된 이모티콘 타입")),
            fieldWithPath("reactions.[].value").type(JsonFieldType.STRING).description("댓글 이모티콘 값").attributes(field("constraint", "지정된 이모티콘 값"))
    };
}
