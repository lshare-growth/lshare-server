package com.example.backend.business.core.member.infrastructure.member.query;

import com.example.backend.business.core.member.entity.Member;
import com.example.backend.business.core.member.entity.values.GithubId;
import com.example.backend.business.core.member.entity.values.MemberId;
import com.example.backend.business.core.member.entity.values.NickName;
import com.example.backend.business.web.member.presentation.member.dto.response.NickNameExistResponse;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import static com.example.backend.business.core.common.Deleted.FALSE;
import static com.example.backend.business.core.member.entity.QMember.member;
import static com.example.backend.business.core.study.entity.QStudyMember.studyMember;
import static com.example.backend.business.core.study.entity.StudyMemberRole.LEADER;

@Repository
public class MemberQueryDslQueryRepository {

    private final JPAQueryFactory queryFactory;

    public MemberQueryDslQueryRepository(JPAQueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }

    public Optional<Member> findById(MemberId memberId) {
        return Optional.ofNullable(
                queryFactory.selectFrom(member)
                .where(
                        member.memberId.eq(memberId.getMemberId())
                                .and(member.deleted.eq(FALSE))
                )
                .fetchOne());
    }

    public Optional<Member> findByGithubId(GithubId githubId) {
        return Optional.ofNullable(queryFactory.selectFrom(member)
                .where(
                        member.githubId.eq(githubId)
                                .and(member.deleted.eq(FALSE))
                )
                .fetchOne());
    }

    public Optional<Member> findByNickName(NickName nickName) {
        return Optional.ofNullable(queryFactory.selectFrom(member)
                .where(
                        member.nickName.eq(nickName)
                                .and(member.deleted.eq(FALSE))
                )
                .fetchOne());
    }

    public NickNameExistResponse validateDuplicatedNickName(NickName nickName) {
        Integer result = queryFactory.selectOne()
                .from(member)
                .where(
                        member.nickName.eq(nickName)
                                .and(member.deleted.eq(FALSE))
                )
                .fetchFirst();
        return NickNameExistResponse.of(result);
    }

    public Boolean validatePageAuthorizationById(Long memberId, Long studyId) {
        if (memberId == null || studyId == null) {
            return Boolean.FALSE;
        }

        Integer result = queryFactory.selectOne()
                .from(studyMember)
                .where(
                        studyMember.study.studyId.eq(studyId)
                                .and(studyMember.member.memberId.eq(memberId))
                                .and(studyMember.studyMemberRole.eq(LEADER))
                )
                .fetchFirst();

        return result != null;
    }
}
