package com.example.backend.business.core.study.entity;

import com.example.backend.business.core.tag.entity.HashTag;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.Objects;

@Entity
public class StudyHashTag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long studyHashTagId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "study_id")
    private Study study;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hash_tag_id")
    private HashTag hashTag;

    /**
     * @Nullary-Constructor. JPA 기본 생성자로 study 외부 패키지에서 호출하지 말 것.
     */
    protected StudyHashTag() {
    }

    public StudyHashTag(Study study, HashTag hashTag) {
        this.study = study;
        this.hashTag = hashTag;
    }

    public static StudyHashTag from(Study study, HashTag hashTag) {
        return new StudyHashTag(study, hashTag);
    }

    public Study getStudy() {
        return study;
    }

    public Long getStudyHashTagId() {
        return studyHashTagId;
    }

    public Long getStudyId() {
        return study.getStudyId();
    }

    public Long getHashTagId(){
        return hashTag.getHashTagId();
    }

    public String getHashTagName(){
        return hashTag.getTagName();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof StudyHashTag)) return false;
        StudyHashTag that = (StudyHashTag) o;
        return hashTag.equals(that.hashTag);
    }

    @Override
    public int hashCode() {
        return Objects.hash(hashTag);
    }

    @Override
    public String toString() {
        return hashTag.toString();
    }
}
