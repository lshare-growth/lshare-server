package com.example.backend.lshare.business.presentation.comment;//package com.example.lshare.lshare.business.presentation.comment;
//
//import com.example.lshare.business.core.study.entity.values.StudyId;
//import com.example.lshare.business.web.comment.presentation.dto.response.CommentResponse;
//import com.example.lshare.business.web.common.page.CustomSlice;
//import com.example.lshare.data.comment.CommentTestData;
//import com.example.lshare.lshare.RestdocsController;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.domain.SliceImpl;
//import org.springframework.http.MediaType;
//import org.springframework.restdocs.payload.FieldDescriptor;
//import org.springframework.restdocs.payload.JsonFieldType;
//import org.springframework.restdocs.request.ParameterDescriptor;
//
//import java.util.List;
//
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.ArgumentMatchers.anyLong;
//import static org.mockito.Mockito.when;
//import static org.springframework.http.MediaType.APPLICATION_JSON;
//import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
//import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
//import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
//import static org.springframework.restdocs.payload.PayloadDocumentation.relaxedResponseFields;
//import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
//import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
//import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@DisplayName("댓글 페이징 API 테스트")
//class CommentPagingApiTest extends RestdocsController {
//
//    @Test
//    @DisplayName("")
//    void COMMENT_PAGING_API() throws Exception {
//        CommentResponse commentResponse = new CommentResponse(CommentTestData.createCommentWithId());
////        commentResponse.addReactions(1L, new CommentReactions(List.from(CommentReactionTestData.createCommentReactionTestDataWithId())));
//
//        List<CommentResponse> content = List.of(commentResponse);
//        Pageable pageable = PageRequest.of(0, 10);
//
//        when(commentQueryFacade.findCommentsByStudyId(anyLong(), StudyId.from(anyLong()), any()))
//                .thenReturn(new CustomSlice<>(new SliceImpl<>(content, pageable, true)));
//
//        mockMvc.perform(get("/api/comments/study/{studyId}", 1L)
//                        .commonHeaders(getOauthRequestHeaders())
//                        .accept(APPLICATION_JSON)
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andDo(document("comment/paging",
//                        pathParameters(COMMENT_PAGING_PATH_PARAMETERS),
//                        relaxedResponseFields(COMMENT_PAGING_CONTENT_RESPONSE_FIELDS)
//                )).andDo(print());
//    }
//
//    private final ParameterDescriptor[] COMMENT_PAGING_PATH_PARAMETERS = new ParameterDescriptor[]{
//            parameterWithName("studyId").description("스터디 아이디")
//    };
//
//    private final FieldDescriptor[] COMMENT_PAGING_CONTENT_RESPONSE_FIELDS = new FieldDescriptor[]{
//            fieldWithPath("content.[].commentId").type(JsonFieldType.NUMBER).description("반환할 댓글 아이디"),
//            fieldWithPath("content.[].writerId").type(JsonFieldType.NUMBER).description("작성자 아이디"),
//            fieldWithPath("content.[].writer").type(JsonFieldType.STRING).description("작성자"),
//            fieldWithPath("content.[].profileImage").type(JsonFieldType.STRING).description("작성자 프로필 이미지 URL"),
//            fieldWithPath("content.[].content").type(JsonFieldType.STRING).description("댓글 내용"),
//            fieldWithPath("content.[].createdAt").type(JsonFieldType.STRING).description("댓글 작성 일자"),
//            fieldWithPath("content.[].lastModifiedAt").type(JsonFieldType.STRING).description("댓글 수정 일자"),
//            fieldWithPath("content.[].reCommentCount").type(JsonFieldType.NUMBER).description("대댓글 개수"),
//
//            fieldWithPath("content.[].reactions.[].emotion").type(JsonFieldType.STRING).description("이모티콘 표정").optional(),
//            fieldWithPath("content.[].reactions.[].count").type(JsonFieldType.NUMBER).description("이모티콘 개수").optional(),
//            fieldWithPath("content.[].reactions.[].reactionClicked").type(JsonFieldType.STRING).description("이모티콘 클릭여부").optional(),
//
//            fieldWithPath("first").type(JsonFieldType.BOOLEAN).description("첫 페이지 여부"),
//            fieldWithPath("last").type(JsonFieldType.BOOLEAN).description("마지막 페이지 여부"),
//            fieldWithPath("sorted").type(JsonFieldType.BOOLEAN).description("정렬 여부"),
//            fieldWithPath("empty").type(JsonFieldType.BOOLEAN).description("컨텐츠가 비어있는지 여부"),
//            fieldWithPath("hasNext").type(JsonFieldType.BOOLEAN).description("다음 페이지 존재 여부"),
//    };
//
//    private final FieldDescriptor[] SUB_FIELDS = new FieldDescriptor[]{
//            fieldWithPath("emotion").type(JsonFieldType.STRING).description("이모티콘 표정"),
//            fieldWithPath("count").type(JsonFieldType.NUMBER).description("이모티콘 개수"),
//            fieldWithPath("reactionClicked").type(JsonFieldType.STRING).description("이모티콘 클릭여부")
//    };
//
//    private final FieldDescriptor[] COMMENT_PAGING_INFORMATION_RESPONSE_FIELDS = new FieldDescriptor[]{
//            fieldWithPath("first").type(JsonFieldType.BOOLEAN).description("첫 페이지 여부"),
//            fieldWithPath("last").type(JsonFieldType.BOOLEAN).description("마지막 페이지 여부"),
//            fieldWithPath("sorted").type(JsonFieldType.BOOLEAN).description("정렬 여부"),
//            fieldWithPath("empty").type(JsonFieldType.BOOLEAN).description("컨텐츠가 비어있는지 여부"),
//            fieldWithPath("hasNext").type(JsonFieldType.BOOLEAN).description("다음 페이지 존재 여부"),
//    };
//}
