package com.example.backend.business.core.study.entity.values;

import com.example.backend.business.core.member.entity.values.StudyJoinRequest;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.OneToMany;
import java.util.LinkedHashSet;
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

    @Override
    public String toString() {
        return studyJoinRequests.toString();
    }
}
