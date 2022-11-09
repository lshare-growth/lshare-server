package com.example.backend.business.core.member.entity.values;

import com.example.backend.business.core.common.ErrorField;
import com.example.backend.business.core.member.entity.Member;
import com.example.backend.business.core.study.entity.values.StudyId;
import com.example.backend.common.exception.BusinessException;
import com.example.backend.common.exception.member.DuplicatedStudyMemberException;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.Objects;

import static com.example.backend.common.exception.member.MemberTypeException.UNAUTHORIZED_EXCEPTION;

@Entity
public class StudyJoinRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long studyJoinRequestId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member studyJoinRequestMember;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "study_leader_id")
    private Member studyLeader;

    @Embedded
    private StudyId studyId;

    @Embedded
    private LastStudyJoinRequestDate requestDate;

    @Column(columnDefinition = "ENUM('NONE', 'REQUEST', 'REJECT', 'APPROVED')")
    @Enumerated(EnumType.STRING)
    private StudyJoinRequestStatus studyJoinRequestStatus;

    @Embedded
    private RejectedCount rejectedCount;

    /**
     * @Nullary-Constructor. JPA 기본 생성자로 member 외부 패키지에서 호출하지 말 것.
     */
    protected StudyJoinRequest() {
    }

    private StudyJoinRequest(Member studyJoinRequestMember, Member studyLeader, StudyId studyId) {
        this.studyJoinRequestMember = studyJoinRequestMember;
        this.studyLeader = studyLeader;
        this.studyJoinRequestStatus = StudyJoinRequestStatus.REQUESTED;
        this.requestDate = LastStudyJoinRequestDate.initStudyJoinRequestDate();
        this.rejectedCount = RejectedCount.initRejectedCount();
        this.studyId = studyId;
    }

    public static StudyJoinRequest createStudyJoinRequest(Member newMember, Member studyLeader, StudyId studyId) {
        return new StudyJoinRequest(newMember, studyLeader, studyId);
    }

    public void register(Member member) {
        this.studyJoinRequestMember = member;
    }

    public Long getStudyJoinRequestId() {
        return studyJoinRequestId;
    }

    public StudyId getStudyId() {
        return studyId;
    }

    public Member getMember() {
        return studyJoinRequestMember;
    }

    public void validateStudyJoinRequest(Member member) {
        if (isAlreadyApproved(member)) {
            throw new DuplicatedStudyMemberException("이미 승인된 멤버입니다.");
        }
        if (isRequestRejectedOverThree(member)) {
            throw new IllegalArgumentException("스터디 거절 횟수 세번을 초과했습니다.", ErrorField.of("member", member));
        }
        if (isRejectedUnderOneHour(member)) {
            throw new IllegalArgumentException("스터디 가입을 신청한지 한시간이 지나지 않았습니다.", ErrorField.of("member", member));
        }
    }

    private boolean isAlreadyApproved(Member member) {
        return this.studyJoinRequestMember.equals(member) && this.studyJoinRequestStatus.equals(StudyJoinRequestStatus.APPROVED);
    }

    private boolean isRequestRejectedOverThree(Member member) {
        return this.studyJoinRequestMember.equals(member) && this.rejectedCount.isOverThree();
    }

    private boolean isRejectedUnderOneHour(Member member) {
        return this.studyJoinRequestMember.equals(member) && this.requestDate.isUnderHour();
    }

    public boolean hasSameStudyId(StudyId studyId) {
        return this.studyId.equals(studyId);
    }

    public void updateStatus(Member studyLeader, Member studyJoinRequestMember, StudyJoinRequestStatus studyJoinRequestStatus) {
        validateStudyJoinRequest(studyLeader, studyJoinRequestMember);
        this.studyJoinRequestStatus = studyJoinRequestStatus;
    }

    private void validateStudyJoinRequest(Member studyLeader, Member studyJoinRequestMember) {
        if (!this.studyLeader.equals(studyLeader) || !this.studyJoinRequestMember.equals(studyJoinRequestMember)) {
            throw new BusinessException(UNAUTHORIZED_EXCEPTION);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof StudyJoinRequest)) return false;
        StudyJoinRequest that = (StudyJoinRequest) o;
        return studyJoinRequestId.equals(that.studyJoinRequestId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(studyJoinRequestId);
    }

    @Override
    public String toString() {
        return studyJoinRequestId.toString();
    }
}
