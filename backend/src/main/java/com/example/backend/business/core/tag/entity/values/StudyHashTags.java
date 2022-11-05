package com.example.backend.business.core.tag.entity.values;

import com.example.backend.business.core.study.entity.StudyHashTag;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.OneToMany;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Embeddable
public class StudyHashTags {

    @OneToMany(mappedBy = "study", cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, orphanRemoval = true)
    private Set<StudyHashTag> studyHashTags;

    /**
     * @Nullary-Constructor. JPA 기본 생성자로 tag 외부 패키지에서 호출하지 말 것.
     */
    protected StudyHashTags() {
        this.studyHashTags = new LinkedHashSet<>();
    }

    private StudyHashTags(Set<StudyHashTag> studyHashTags) {
        validateCreateHashTags(studyHashTags);
        this.studyHashTags = studyHashTags;
    }

    private static void validateCreateHashTags(Set<StudyHashTag> hashTags) {
        if (Objects.isNull(hashTags) || hashTags.isEmpty()) {
            throw new IllegalArgumentException("해시태그 값을 입력해주세요.");
        }
        if (hashTags.size() > 8) {
            throw new IllegalArgumentException("해시태그는 최대 8개까지 입력할 수 있습니다.");
        }
    }

    public static StudyHashTags initHashTags() {
        return new StudyHashTags();
    }

    public static StudyHashTags from(Set<StudyHashTag> studyHashTags) {
        validateCreateHashTags(studyHashTags);
        return new StudyHashTags(studyHashTags);
    }

    public void add(StudyHashTag studyHashTag) {
        this.studyHashTags.add(studyHashTag);
    }

    public Set<StudyHashTag> getStudyHashTags() {
        return studyHashTags;
    }

    public void updateHashTags(StudyHashTags hashTags) {
        if (hashTags.isEmpty()) {
            return;
        }
        if (hashTags.size() > 8) {
            throw new IllegalArgumentException("해시태그는 최대 8개까지 입력할 수 있습니다.");
        }

        Set<StudyHashTag> differenceOfSets = new LinkedHashSet<>();

        for (StudyHashTag studyHashTag : this.studyHashTags) {
            if (!hashTags.getStudyHashTags().contains(studyHashTag)) {
                differenceOfSets.add(studyHashTag);
            }
        }

        this.studyHashTags.removeAll(differenceOfSets);

        for (StudyHashTag studyHashTag : hashTags.getStudyHashTags()) {
            if (!this.studyHashTags.contains(studyHashTag)) {
                this.studyHashTags.add(studyHashTag);
            }
        }
    }

    private boolean isEmpty() {
        return this.studyHashTags == null || this.studyHashTags.isEmpty();
    }

    public int size() {
        return this.studyHashTags.size();
    }

    public void validate() {
        if (this.studyHashTags == null || this.studyHashTags.isEmpty()) {
            throw new IllegalArgumentException("해시태그가 존재하지 않습니다.");
        }
    }

    @Override
    public String toString() {
        return studyHashTags.toString();
    }
}
