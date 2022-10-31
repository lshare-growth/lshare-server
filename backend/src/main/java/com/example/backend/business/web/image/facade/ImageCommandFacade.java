package com.example.backend.business.web.image.facade;

import com.example.backend.business.web.image.application.ImageQueryService;
import com.example.backend.business.web.image.presentation.response.StudyImageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ImageCommandFacade {

    private final ImageQueryService imageQueryService;

    public List<StudyImageResponse> findStudyImagesById(Long studyId) {
        return imageQueryService.findStudyImagesById(studyId);
    }
}
