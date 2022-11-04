package com.example.backend.lshare.business.presentation.study;

import com.example.backend.lshare.RestdocsController;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.restdocs.request.ParameterDescriptor;

import static com.example.backend.data.study.StudyTestData.STUDY_ID;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.delete;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("스터디 삭제 API")
class StudyDeleteApiDocumentationTest extends RestdocsController {

    @Test
    @DisplayName("")
    void STUDY_DELETE_API() throws Exception {
        mockMvc.perform(delete("/api/studies/{studyId}", STUDY_ID)
                        .requestAttr("memberId", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .headers(getOauthRequestHeaders()))
                .andExpect(status().isNoContent())
                .andDo(document("study/delete",
                        pathParameters(STUDY_DELETE_PATH_PARAMETER)))
                .andDo(print());
    }

    private static final ParameterDescriptor STUDY_DELETE_PATH_PARAMETER =
            parameterWithName("studyId").description("삭제할 스터디 아이디");
}
