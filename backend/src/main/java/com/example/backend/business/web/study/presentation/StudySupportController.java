package com.example.backend.business.web.study.presentation;

import com.example.backend.business.web.study.presentation.dto.response.DistrictResponse;
import com.example.backend.business.web.study.presentation.dto.response.ProgressOfStudyResponse;
import com.example.backend.common.mapper.api.EnumMapper;
import com.example.backend.common.mapper.api.EnumMapperValue;
import lombok.RequiredArgsConstructor;
import org.springframework.http.CacheControl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.example.backend.common.mapper.keys.CacheKey.DISTRICTS;
import static com.example.backend.common.mapper.keys.CacheKey.PROGRESS_OF_STUDY;
import static java.util.concurrent.TimeUnit.DAYS;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/study-supports")
public class StudySupportController {

    private final EnumMapper enumMapper;

    @GetMapping("/districts")
    public ResponseEntity<DistrictResponse> findDistrict() {

        List<EnumMapperValue> districts = enumMapper.getValues(
                DISTRICTS
        );

        return ResponseEntity.ok()
                .eTag("study-register-supports")
                .cacheControl(CacheControl.maxAge(30, DAYS))
                .body(DistrictResponse.of(districts));
    }

    @GetMapping("/progress-of-study")
    public ResponseEntity<ProgressOfStudyResponse> findProgressOfStudy() {

        List<EnumMapperValue> progressOfStudy = enumMapper.getValues(
                PROGRESS_OF_STUDY
        );

        return ResponseEntity.ok()
                .eTag("study-register-supports")
                .cacheControl(CacheControl.maxAge(30, DAYS))
                .body(ProgressOfStudyResponse.of(progressOfStudy));
    }
}
