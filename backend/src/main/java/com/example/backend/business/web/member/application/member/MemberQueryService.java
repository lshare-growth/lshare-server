package com.example.backend.business.web.member.application.member;

import com.example.backend.business.core.member.entity.Member;
import com.example.backend.business.core.member.entity.values.GithubId;
import com.example.backend.business.core.member.entity.values.MemberId;
import com.example.backend.business.core.member.entity.values.NickName;
import com.example.backend.business.core.member.infrastructure.member.query.MemberQueryDslQueryRepository;
import com.example.backend.business.web.member.presentation.member.dto.response.NickNameExistResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberQueryService {

    private final MemberQueryDslQueryRepository memberQueryDslQueryRepository;

    @Transactional(readOnly = true)
    public Optional<Member> findById(MemberId memberId) {
        return memberQueryDslQueryRepository.findById(memberId);
    }

    @Transactional(readOnly = true)
    public Optional<Member> findByGithubId(GithubId githubId) {
        return memberQueryDslQueryRepository.findByGithubId(githubId);
    }

    @Transactional(readOnly = true)
    public Optional<Member> findByNickName(NickName nickName) {
        return memberQueryDslQueryRepository.findByNickName(nickName);
    }

    @Transactional(readOnly = true)
    public NickNameExistResponse validateDuplicatedNickName(NickName nickName) {
        return memberQueryDslQueryRepository.validateDuplicatedNickName(nickName);
    }
}
