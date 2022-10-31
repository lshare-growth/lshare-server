package com.example.backend.business.web.image.presentation;

import com.example.backend.business.web.image.facade.ImageQueryFacade;
import com.example.backend.business.web.image.presentation.response.StudyImagesResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/images")
public class ImageController {

    private final ImageQueryFacade imageQueryFacade;

    @GetMapping("{studyId}")
    public ResponseEntity<StudyImagesResponse> findStudyImagesById(Long studyId) {
        return ResponseEntity.ok(StudyImagesResponse.of(imageQueryFacade.findStudyImagesById(studyId)));
    }

    @PostMapping("/study/{studyId}")
    public ResponseEntity<Void> uploadImages(@RequestParam(value = "image", required = false) List<MultipartFile> files) {
        imageQueryFacade.upload(files);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/study/{studyId}")
    public ResponseEntity<Void> deleteImages(@PathVariable("studyId") Long studyId) {
        return ResponseEntity.ok().build();
    }
}
