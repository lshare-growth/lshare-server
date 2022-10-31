package com.example.backend.lshare.business.presentation.study.query;

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
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("스터디 서포트 API")
class StudySupportControllerApiDocumentationTest extends RestdocsController {

    @Test
    @DisplayName("스터디 지역 목록 API Documentation")
    void DISTRICSTS_GET_API() throws Exception {
        when(enumMapper.getValues(anyString())).thenReturn(MapperTestData.getDistrictsTestData());

        mockMvc.perform(get("/api/study-supports/districts")
                        .contentType(APPLICATION_JSON)
                        .accept(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(document("study-supports/districsts",
                        responseFields(STUDY_SUPPORT_DISTRICTS_RESPONSE_FIELDS)));
    }

    private final FieldDescriptor[] STUDY_SUPPORT_DISTRICTS_RESPONSE_FIELDS = new FieldDescriptor[]{
            fieldWithPath("districts.[].id").type(JsonFieldType.NUMBER).description("스터디 지역 아이디").attributes(field("constraint", "Not Null")),
            fieldWithPath("districts.[].type").type(JsonFieldType.STRING).description("스터디 지역 타입").attributes(field("constraint", "지정된 이모티콘 타입")),
            fieldWithPath("districts.[].value").type(JsonFieldType.STRING).description("스터디 지역 값").attributes(field("constraint", "지정된 이모티콘 값"))
    };

    @Test
    @DisplayName("스터디 진행방식 목록 API Documentation")
    void PROGRESS_OF_STUDY_GET_API() throws Exception {
        when(enumMapper.getValues(anyString())).thenReturn(MapperTestData.getProgressOfStudies());

        mockMvc.perform(get("/api/study-supports/progress-of-study")
                        .contentType(APPLICATION_JSON)
                        .accept(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(document("study-supports/progress-from-study-list",
                        responseFields(STUDY_SUPPORT_PROGRESS_OF_STUDY_RESPONSE_FIELDS)))
                .andDo(print());
    }

    private static final FieldDescriptor[] STUDY_SUPPORT_PROGRESS_OF_STUDY_RESPONSE_FIELDS = new FieldDescriptor[]{
            fieldWithPath("progressOfStudies.[].id").type(JsonFieldType.NUMBER).description("스터디 진행방식 아이디"),
            fieldWithPath("progressOfStudies.[].type").type(JsonFieldType.STRING).description("스터디 진행방식 타입"),
            fieldWithPath("progressOfStudies.[].value").type(JsonFieldType.STRING).description("스터디 진행방식 값")
    };
}
