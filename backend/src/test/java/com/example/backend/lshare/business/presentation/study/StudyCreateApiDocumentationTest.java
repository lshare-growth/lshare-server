package com.example.backend.lshare.business.presentation.study;

import com.example.backend.business.core.member.entity.values.MemberId;
import com.example.backend.business.core.study.entity.Study;
import com.example.backend.business.core.tag.entity.values.TagNames;
import com.example.backend.business.web.study.presentation.dto.request.StudyRegisterRequest;
import com.example.backend.data.study.StudyTestData;
import com.example.backend.lshare.RestdocsController;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.FieldDescriptor;
import org.springframework.restdocs.payload.JsonFieldType;

import java.time.LocalDate;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("스터디 개설 API")
class StudyCreateApiDocumentationTest extends RestdocsController {

    @Test
    @DisplayName("")
    void STUDY_CREATE_API() throws Exception {
        StudyRegisterRequest request = new StudyRegisterRequest(
                "Backend 쿼리 최적화 스터디 모집합니다.",
                "소수로 운영될 예정이며 온라인으로 주말에 스터디를 진행할 예정입니다. 주 2회스터디로 여러가지 예제를 공유해주시면 감사하겠습니다.",
                LocalDate.now(),
                LocalDate.now().plusDays(100),
                4,
                "SEOUL",
                "ONLINE",
                List.of("Backend", "RealMySQL", "Test"));

        when(studyFacade.createStudy(any(MemberId.class), any(Study.class), any(TagNames.class)))
                .thenReturn(StudyTestData.createStudyTestDataWithId());

        mockMvc.perform(post("/api/studies")
                        .accept(MediaType.APPLICATION_JSON)
                        .requestAttr("memberId", 1L)
                        .headers(getOauthRequestHeaders())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andDo(document("study/create",
                        requestFields(STUDY_CREATE_REQUEST_FIELDS),
                        responseFields(STUDY_CREATE_RESPONSE_FIELDS)));
    }

    private final FieldDescriptor[] STUDY_CREATE_REQUEST_FIELDS = new FieldDescriptor[]{
            fieldWithPath("title").type(JsonFieldType.STRING).description("개설할 스터디 제목"),
            fieldWithPath("content").type(JsonFieldType.STRING).description("개설할 스터디 내용"),
            fieldWithPath("startDate").type(JsonFieldType.STRING).description("개설할 스터디 시작 날짜"),
            fieldWithPath("endDate").type(JsonFieldType.STRING).description("개설할 스터디 종료 날짜"),
            fieldWithPath("maxStudyMemberCount").type(JsonFieldType.NUMBER).description("개설할 최대 스터디 인원"),
            fieldWithPath("district").type(JsonFieldType.STRING).description("개설할 스터디 진행 지역"),
            fieldWithPath("progressOfStudy").type(JsonFieldType.STRING).description("개설할 스터디 진행 방식"),
            fieldWithPath("hashTags").type(JsonFieldType.ARRAY).description("개설할 스터디 해시태그")
    };

    private final FieldDescriptor[] STUDY_CREATE_RESPONSE_FIELDS = new FieldDescriptor[]{
            fieldWithPath("studyId").type(JsonFieldType.NUMBER).description("개설된 스터디 아이디")
    };
}
