package com.example.backend.business.web.image.facade;

import com.example.backend.business.web.image.application.ImageQueryService;
import com.example.backend.business.web.image.presentation.response.StudyImageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ImageQueryFacade {

    private final ImageQueryService imageQueryService;

    @Transactional(readOnly = true)
    public List<StudyImageResponse> findStudyImagesById(Long studyId) {
        return imageQueryService.findStudyImagesById(studyId);
    }

    public void upload(List<MultipartFile> files) {
        //
//        ImageFiles imageFiles = new ImageFiles(files);
//        Images images = new Images(imageFiles.getImageFiles().stream()
//                .map(this::toEntity)
//                .collect(Collectors.toUnmodifiableList()));
//
//        imageQueryService.save(images);
        // image 엔티티 생성

        // S3 업로드
    }
//
//    private Image toEntity(ImageFile imageFile) {
//        return new Image(imageFile.getOriginalFileName());
//    }
}
