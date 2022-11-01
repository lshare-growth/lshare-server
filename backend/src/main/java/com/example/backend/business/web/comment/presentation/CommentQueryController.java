package com.example.backend.business.web.comment.presentation;

import com.example.backend.business.core.comment.entity.values.CommentParentId;
import com.example.backend.business.core.study.entity.values.StudyId;
import com.example.backend.business.web.comment.facade.CommentQueryFacade;
import com.example.backend.business.web.comment.presentation.dto.response.CommentPageResponse;
import com.example.backend.business.web.comment.presentation.dto.response.CommentResponse;
import com.example.backend.business.web.common.page.CustomSlice;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

import static com.example.backend.common.api.ApiUtils.getMemberId;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/comments")
public class CommentQueryController {

    private final CommentQueryFacade commentQueryFacade;

    @GetMapping("/study/{studyId}")
    public ResponseEntity<CustomSlice<CommentResponse>> findById(@PathVariable Long studyId,
                                                                 Pageable pageable,
                                                                 HttpServletRequest httpServletRequest) {

        CustomSlice<CommentResponse> response = commentQueryFacade.findById(
                getMemberId(httpServletRequest),
                StudyId.from(studyId),
                pageable
        );

        return ResponseEntity.ok(response);
    }

    @GetMapping("/study/{studyId}/v2")
    public ResponseEntity<CommentPageResponse> findByV2(@PathVariable Long studyId,
                                                        Pageable pageable,
                                                        HttpServletRequest httpServletRequest) {

        CommentPageResponse response = commentQueryFacade.findByIdV2(
                getMemberId(httpServletRequest),
                StudyId.from(studyId),
                pageable
        );

        return ResponseEntity.ok(response);
    }

    @GetMapping("/commentParent/{commentParentId}")
    public ResponseEntity<CustomSlice<CommentResponse>> findByParentId(@PathVariable Long commentParentId,
                                                                       Pageable pageable,
                                                                       HttpServletRequest httpServletRequest) {

        CustomSlice<CommentResponse> response = commentQueryFacade.findByParentId(
                getMemberId(httpServletRequest),
                CommentParentId.from(commentParentId),
                pageable
        );

        return ResponseEntity.ok(response);
    }
}
