package com.example.backend.business.web.member.presentation.follow;

import com.example.backend.business.core.member.entity.values.MemberId;
import com.example.backend.business.web.member.facade.follow.FollowCommandFacade;
import com.example.backend.business.web.member.presentation.follow.dto.request.FollowRequest;
import com.example.backend.common.login.annotation.Authenticated;
import com.example.backend.common.login.model.authentication.AuthenticatedMember;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/members")
public class FollowCommandController {

    private final FollowCommandFacade followCommandFacade;

    @PostMapping("/following")
    public ResponseEntity<Void> updateFollow(@Authenticated AuthenticatedMember authenticatedMember,
                                             @RequestBody FollowRequest request) {

        followCommandFacade.updateFollow(
                authenticatedMember.getAuthenticatedIdAsValue(),
                MemberId.from(request.getTargetId())
        );

        return ResponseEntity.ok().build();
    }
}
