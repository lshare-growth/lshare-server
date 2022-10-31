package com.example.backend.business.core.member.entity.values;

import com.example.backend.business.core.member.entity.Member;
import com.example.backend.business.core.study.entity.StudyMember;
import com.example.backend.common.exception.BusinessException;
import com.example.backend.common.exception.member.DuplicatedStudyMemberException;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.OneToMany;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

import static com.example.backend.business.core.study.entity.StudyMemberRole.LEADER;
import static com.example.backend.common.exception.member.MemberTypeException.MEMBER_NOT_FOUND_EXCEPTION;
import static com.example.backend.common.exception.member.MemberTypeException.STUDY_LEADER_NOT_FOUND_EXCEPTION;
import static com.example.backend.common.exception.member.MemberTypeException.UNAUTHORIZED_EXCEPTION;

@Embeddable
public class StudyMembers {

    @OneToMany(mappedBy = "study", cascade = CascadeType.PERSIST)
    private Set<StudyMember> studyMembers;

    /**
     * @Nullary-Constructor. JPA 기본 생성자로 member 외부 패키지에서 호출하지 말 것.
     */
    protected StudyMembers() {
        this.studyMembers = new LinkedHashSet<>();
    }

    private StudyMembers(Set<StudyMember> studyMembers) {
        this.studyMembers = studyMembers;
    }

    public static StudyMembers from(Set<StudyMember> studyMembers) {
        return new StudyMembers(studyMembers);
    }

    public static StudyMembers initStudyMembers() {
        return new StudyMembers();
    }

    public Set<StudyMember> getStudyMembers() {
        return studyMembers;
    }

    private static boolean isStudyLeader(StudyMember studyMember) {
        return studyMember.getRole().equals(LEADER);
    }

    public void addStudyMember(StudyMember studyMember) {
        validateStudyMembers(studyMember);
        this.studyMembers.add(studyMember);
    }

    public void validateStudyMembers(StudyMember studyMember) {
        if (Objects.isNull(studyMember)) {
            throw new IllegalArgumentException("스터디 멤버가 존재하지 않습니다.");
        }
        if (studyMembers.contains(studyMember)) {
            throw new DuplicatedStudyMemberException("이미 존재하는 스터디멤버입니다.");
        }
        studyMembers.add(studyMember);
    }

    public Member findStudyLeader() {
        return studyMembers.stream()
                .filter(StudyMember::isStudyLeader)
                .findFirst()
                .map(StudyMember::getMember)
                .orElseThrow(() -> new BusinessException(STUDY_LEADER_NOT_FOUND_EXCEPTION));
    }

    public void validateStudyLeader(Member studyLeader) {
        Member findStudyLeader = findStudyLeader();

        if (findStudyLeader != studyLeader) {
            throw new BusinessException(UNAUTHORIZED_EXCEPTION);
        }
    }

    public int getStudyMembersSize() {
        return studyMembers.size();
    }

    public void validateDuplicatedMember(Member newMember) {
        for (StudyMember studyMember : this.studyMembers) {
            if (studyMember.getMember().equals(newMember)) {
                throw new DuplicatedStudyMemberException();
            }
        }
    }

    public String getStudyLeaderNickName() {
        Optional<StudyMember> findStudyMember = studyMembers.stream()
                .filter(StudyMembers::isStudyLeader)
                .findAny();

        if (findStudyMember.isPresent()) {
            StudyMember studyLeader = findStudyMember.get();
            return studyLeader.getStudyLeaderNickName();
        }

        throw new BusinessException(MEMBER_NOT_FOUND_EXCEPTION);
    }

    @Override
    public String toString() {
        return String.format("스터디에 참여중인 회원 목록: %s", studyMembers.toString());
    }
}
