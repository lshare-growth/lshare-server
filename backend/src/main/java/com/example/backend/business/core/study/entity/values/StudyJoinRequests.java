package com.example.backend.business.core.study.entity.values;

import com.example.backend.business.core.member.entity.Member;
import com.example.backend.business.core.member.entity.values.StudyJoinRequest;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.OneToMany;
import java.util.LinkedHashSet;
import java.util.Optional;
import java.util.Set;

@Embeddable
public class StudyJoinRequests {

    @OneToMany(mappedBy = "studyJoinRequestMember", cascade = CascadeType.PERSIST, orphanRemoval = true)
    private Set<StudyJoinRequest> studyJoinRequests;

    /**
     * @Nullary-Constructor. JPA 기본 생성자로 study 외부 패키지에서 호출하지 말 것.
     */
    protected StudyJoinRequests() {
        this(new LinkedHashSet<>());
    }

    public StudyJoinRequests(Set<StudyJoinRequest> studyJoinRequests) {
        this.studyJoinRequests = studyJoinRequests;
    }

    public static StudyJoinRequests initStudyJoinRequests() {
        return new StudyJoinRequests();
    }

    public void addJoinRequest(StudyJoinRequest studyJoinRequest) {
        this.studyJoinRequests.add(studyJoinRequest);
    }

    public void validateStudyJoinRequest(Member member, StudyId studyId) {
        Optional<StudyJoinRequest> findStudyJoinRequest = this.studyJoinRequests.stream()
                .filter(studyJoinRequest -> studyJoinRequest.hasSameStudyId(studyId))
                .findAny();

        findStudyJoinRequest.ifPresent(studyJoinRequest -> studyJoinRequest.validateStudyJoinRequest(member));
    }

    public StudyJoinRequest findStudyJoinRequest(Long studyJoinRequestId) {
        return this.studyJoinRequests.stream()
                .filter(x -> x.getStudyJoinRequestId().equals(studyJoinRequestId))
                .findAny()
                .orElseThrow();
    }

    @Override
    public String toString() {
        return studyJoinRequests.toString();
    }
}
