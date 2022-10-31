package com.example.backend.business.web.member.presentation.follow;

import com.example.backend.business.core.member.entity.values.MemberId;
import com.example.backend.business.web.member.facade.member.MemberCommandFacade;
import com.example.backend.business.web.member.presentation.member.dto.request.FollowRequest;
import com.example.backend.common.login.annotation.Authenticated;
import com.example.backend.common.login.model.authentication.AuthenticatedMember;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/members")
@RequiredArgsConstructor
public class FollowCommandController {

    private final MemberCommandFacade memberCommandFacade;

    @PostMapping("/following")
    public ResponseEntity<Void> updateFollow(@Authenticated AuthenticatedMember authenticatedMember,
                                             @RequestBody FollowRequest request) {

        memberCommandFacade.updateFollow(
                authenticatedMember.getAuthenticatedIdAsValue(),
                MemberId.from(request.getTargetId())
        );

        return ResponseEntity.ok().build();
    }
}