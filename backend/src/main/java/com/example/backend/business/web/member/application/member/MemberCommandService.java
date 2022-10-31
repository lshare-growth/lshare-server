package com.example.backend.business.web.member.application.member;

import com.example.backend.business.core.common.District;
import com.example.backend.business.core.member.entity.Member;
import com.example.backend.business.core.member.entity.values.BirthDate;
import com.example.backend.business.core.member.entity.values.BlogUrl;
import com.example.backend.business.core.member.entity.values.Introduction;
import com.example.backend.business.core.member.entity.values.NickName;
import com.example.backend.business.core.member.infrastructure.member.MemberJpaRepository;
import com.example.backend.business.core.member.infrastructure.member.command.MemberQueryDslCommandRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberCommandService {

    private final MemberJpaRepository memberJpaRepository;
    private final MemberQueryDslCommandRepository memberQueryRepository;

    @Transactional
    public Member save(Member member) {
        return memberJpaRepository.save(member);
    }

    @Transactional
    public void updateProfile(Member member,
                              BlogUrl blogUrl,
                              District district,
                              BirthDate birthDate,
                              Introduction introduction) {

        member.updateProfile(blogUrl, district, birthDate, introduction);
    }

    @Transactional
    public void updatNickName(Member member, NickName nickName) {
        member.updateNickName(nickName);
    }

    @Transactional
    public void delete(Member member) {
        member.delete();
    }
}
