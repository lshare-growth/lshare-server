package com.example.backend.lshare.business.presentation.study;

import com.example.backend.business.web.study.presentation.dto.request.StudyUpdateRequest;
import com.example.backend.lshare.RestdocsController;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.FieldDescriptor;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.restdocs.request.ParameterDescriptor;

import java.time.LocalDate;
import java.util.List;

import static com.example.backend.data.study.StudyTestData.STUDY_ID;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.put;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("스터디 수정 API")
class StudyUpdateApiDocumentationTest extends RestdocsController {

    @Test
    @DisplayName("")
    void STUDY_UPDATE_API_TEST() throws Exception {
        StudyUpdateRequest request = new StudyUpdateRequest("Backend 쿼리 최적화 스터디 모집합니다.",
                "소수로 운영될 예정이며 온라인으로 주말에 스터디를 진행할 예정입니다. 주 2회스터디로 여러가지 예제를 공유해주시면 감사하겠습니다.",
                4,
                "SEOUL",
                "ONLINE",
                LocalDate.now(),
                LocalDate.now().plusDays(100),
                List.of("Backend", "RealMySQL", "Test"));

        mockMvc.perform(put("/api/studies/{studyId}", STUDY_ID)
                        .headers(getOauthRequestHeaders())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andDo(document("study/update",
                        pathParameters(STUDY_UPDATE_PATH_PARAMETER),
                        requestFields(STUDY_UPDATE_REQUEST_FIELDS)))
                .andDo(print());
    }

    private static final ParameterDescriptor STUDY_UPDATE_PATH_PARAMETER =
            parameterWithName("studyId").description("수정할 스터디 아이디");

    private final FieldDescriptor[] STUDY_UPDATE_REQUEST_FIELDS = new FieldDescriptor[]{
            fieldWithPath("title").type(JsonFieldType.STRING).description("수정할 스터디 제목"),
            fieldWithPath("content").type(JsonFieldType.STRING).description("수정할 스터디 내용"),
            fieldWithPath("maxStudyMemberCount").type(JsonFieldType.NUMBER).description("수정할 최대 스터디 인원"),
            fieldWithPath("district").type(JsonFieldType.STRING).description("수정할 스터디 진행 지역"),
            fieldWithPath("progressOfStudy").type(JsonFieldType.STRING).description("수정할 스터디 진행 방식"),
            fieldWithPath("startDate").type(JsonFieldType.STRING).description("수정할 스터디 시작 날짜"),
            fieldWithPath("endDate").type(JsonFieldType.STRING).description("수정할 스터디 종료 날짜"),
            fieldWithPath("hashTags").type(JsonFieldType.ARRAY).description("수정할 스터디 해시태그")
    };
}
