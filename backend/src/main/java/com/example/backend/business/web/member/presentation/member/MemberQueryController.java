package com.example.backend.business.web.member.presentation.member;

import com.example.backend.business.core.member.entity.Member;
import com.example.backend.business.core.member.entity.values.NickName;
import com.example.backend.business.web.member.facade.member.MemberQueryFacade;
import com.example.backend.business.web.member.presentation.member.dto.response.MemberInformationHoverResponse;
import com.example.backend.business.web.member.presentation.member.dto.response.MemberProfileResponse;
import com.example.backend.business.web.member.presentation.member.dto.response.NickNameExistResponse;
import com.example.backend.common.login.annotation.Authenticated;
import com.example.backend.common.login.model.authentication.AuthenticatedMember;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/members")
public class MemberQueryController {

    private final MemberQueryFacade memberQueryFacade;

    @GetMapping("/my-profile")
    public ResponseEntity<MemberProfileResponse> findMemberById(@Authenticated AuthenticatedMember authenticatedMember) {

        Member findMember = memberQueryFacade.findMemberById(
                authenticatedMember.getAuthenticatedIdAsValue()
        );

        return ResponseEntity.ok(MemberProfileResponse.of(findMember));
    }

    @GetMapping
    public ResponseEntity<MemberInformationHoverResponse> findMemberByNickName(@RequestParam(required = false) String nickName) {

        if (isBlank(nickName)) {
            return ResponseEntity.notFound().build();
        }

        Member findMember = memberQueryFacade.findMemberByNickName(
                NickName.from(nickName)
        );

        return ResponseEntity.ok(MemberInformationHoverResponse.of(findMember));
    }

    @GetMapping("/duplicated-nickName")
    public ResponseEntity<NickNameExistResponse> validateDuplicatedNickName(@Authenticated AuthenticatedMember authenticatedMember,
                                                                            @RequestParam String nickName) {

        NickNameExistResponse response = memberQueryFacade.validateDuplicatedNickName(
                authenticatedMember.getAuthenticatedIdAsValue(),
                NickName.from(nickName)
        );

        return ResponseEntity.ok(response);
    }

    private boolean isBlank(String nickName) {
        return Objects.isNull(nickName) || nickName.isBlank() || nickName.trim().isBlank();
    }
}
