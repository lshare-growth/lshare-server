package com.example.backend.business.core.study.entity;

import com.example.backend.business.core.member.entity.Member;
import com.example.backend.business.core.member.entity.values.LastStudyJoinRequestDate;
import com.example.backend.business.core.member.entity.values.RejectedCount;
import com.example.backend.business.core.member.entity.values.StudyJoinRequestStatus;
import com.example.backend.business.core.study.entity.values.StudyJoinRequests;

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

@Entity
public class StudyMember {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long studyMemberId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "study_id")
    private Study study;

    @Embedded
    private StudyJoinRequests studyJoinRequests;

    @Column(columnDefinition = "ENUM('LEADER', 'MEMBER')")
    @Enumerated(EnumType.STRING)
    private StudyMemberRole studyMemberRole;

    @Column(columnDefinition = "ENUM('NONE', 'REQUEST', 'REJECT', 'APPROVED')")
    @Enumerated(EnumType.STRING)
    private StudyJoinRequestStatus joinRequestStatus;

    @Embedded
    private RejectedCount rejectedCount;

    @Embedded
    private LastStudyJoinRequestDate lastStudyJoinRequestDate;

    /**
     * @Nullary-Constructor. JPA 기본 생성자로 study 외부 패키지에서 호출하지 말 것.
     */
    protected StudyMember() {
    }

    public StudyMember(Member member,
                       Study study,
                       StudyMemberRole studyMemberRole) {

        this.member = member;
        this.study = study;
        this.joinRequestStatus = StudyJoinRequestStatus.NONE;
        this.rejectedCount = RejectedCount.initRejectedCount();
        this.lastStudyJoinRequestDate = LastStudyJoinRequestDate.initStudyJoinRequestDate();
        this.studyMemberRole = studyMemberRole;
    }

    public boolean isStudyLeader() {
        return this.studyMemberRole.equals(StudyMemberRole.LEADER);
    }

    public Member getMember() {
        return member;
    }

    public Study getStudy() {
        return study;
    }

    public StudyMemberRole getRole() {
        return studyMemberRole;
    }

    public boolean is(final Member member) {
        return this.member.equals(member);
    }

    public String getMemberProfileImageUrl() {
        return member.getProfileImageUrl();
    }

    public String getStudyLeaderNickName() {
        return member.getNickName();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof StudyMember)) return false;
        StudyMember that = (StudyMember) o;
        return getMember().equals(that.getMember()) && getStudy().equals(that.getStudy());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getMember(), getStudy());
    }

    @Override
    public String toString() {
        return studyMemberId.toString();
    }
}
