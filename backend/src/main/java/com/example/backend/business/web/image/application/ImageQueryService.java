package com.example.backend.business.web.image.application;

import com.example.backend.business.core.image.entity.vo.Images;
import com.example.backend.business.core.image.infrastructure.command.ImageQueryDslCommandRepository;
import com.example.backend.business.web.image.presentation.response.StudyImageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ImageQueryService {

    private final ImageQueryDslCommandRepository imageQuerydslCommandRepository;

    @Transactional(readOnly = true)
    public List<StudyImageResponse> findStudyImagesById(Long studyId) {
        return imageQuerydslCommandRepository.findStudyImagesById(studyId).stream()
                .map(StudyImageResponse::new)
                .collect(Collectors.toUnmodifiableList());
    }

    @Transactional
    public void save(Images images) {

    }
}
