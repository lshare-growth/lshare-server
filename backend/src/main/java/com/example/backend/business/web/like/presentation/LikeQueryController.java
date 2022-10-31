package com.example.backend.business.web.like.presentation;

import com.example.backend.business.core.like.entity.LikeClicked;
import com.example.backend.business.core.study.entity.values.StudyId;
import com.example.backend.business.web.like.facade.LikeQueryFacade;
import com.example.backend.business.web.like.presentation.dto.response.LikeClickedResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

import static com.example.backend.business.core.like.entity.LikeClicked.FALSE;
import static com.example.backend.common.api.ApiUtils.getMemberId;
import static com.example.backend.common.api.ApiUtils.getMemberIdAsValue;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/likes")
public class LikeQueryController {

    private final LikeQueryFacade likeQueryFacade;

    @GetMapping("/study/{studyId}")
    public ResponseEntity<LikeClickedResponse> exist(@PathVariable Long studyId,
                                                     HttpServletRequest httpServletRequest) {

        if (Objects.isNull(getMemberId(httpServletRequest))) {
            return ResponseEntity.ok(LikeClickedResponse.of(FALSE));
        }

        LikeClicked likeClicked = likeQueryFacade.exist(
                getMemberIdAsValue(httpServletRequest),
                StudyId.from(studyId)
        );

        return ResponseEntity.ok(LikeClickedResponse.of(likeClicked));
    }
}
