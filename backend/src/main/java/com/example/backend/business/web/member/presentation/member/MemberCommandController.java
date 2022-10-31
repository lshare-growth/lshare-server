package com.example.backend.business.web.member.presentation.member;

import com.example.backend.business.core.member.entity.values.BirthDate;
import com.example.backend.business.core.member.entity.values.BlogUrl;
import com.example.backend.business.core.member.entity.values.Introduction;
import com.example.backend.business.core.member.entity.values.NickName;
import com.example.backend.business.web.member.facade.member.MemberCommandFacade;
import com.example.backend.business.web.member.presentation.member.dto.request.MemberNickNameUpdateRequest;
import com.example.backend.business.web.member.presentation.member.dto.request.MemberProfileUpdateRequest;
import com.example.backend.common.login.annotation.Authenticated;
import com.example.backend.common.login.model.authentication.AuthenticatedMember;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/members")
public class MemberCommandController {

    private final MemberCommandFacade memberCommandFacade;

    @PutMapping("/my-profile")
    public ResponseEntity<Void> updateProfile(@Authenticated AuthenticatedMember authenticatedMember,
                                              @RequestBody MemberProfileUpdateRequest request) {

        memberCommandFacade.updateProfile(
                authenticatedMember.getAuthenticatedIdAsValue(),
                BlogUrl.from(request.getBlogUrl()),
                request.getDistrictAsEnum(),
                BirthDate.from(request.getBirthDate()),
                Introduction.from(request.getIntroduction())
        );

        return ResponseEntity.ok().build();
    }

    @PutMapping("/my-profile/nickName")
    public ResponseEntity<Void> updateNickName(@Authenticated AuthenticatedMember authenticatedMember,
                                               @RequestBody MemberNickNameUpdateRequest request) {

        memberCommandFacade.updateNickName(
                authenticatedMember.getAuthenticatedIdAsValue(),
                NickName.from(request.getNickName())
        );

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/withdrawal")
    public ResponseEntity<Void> withdrawalMembership(@Authenticated AuthenticatedMember authenticatedMember) {

        memberCommandFacade.withdrawalMembership(
                authenticatedMember.getAuthenticatedIdAsValue()
        );

        return ResponseEntity.ok().build();
    }
}
