package com.example.backend.business.core.like.entity;

import com.example.backend.business.core.member.entity.values.MemberId;
import com.example.backend.business.core.study.entity.Study;
import com.fasterxml.uuid.Generators;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "likes")
public class Like {

    @Id
    @Column(columnDefinition = "BINARY(16)")
    private java.util.UUID UUID;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "study_id")
    private Study study;

    @Embedded
    private MemberId memberId;

    @Column(columnDefinition = "ENUM('TRUE', 'FALSE')")
    @Enumerated(EnumType.STRING)
    private LikeClicked clicked;

    /**
     * @Nullary-Constructor. JPA 기본 생성자로 study 외부 패키지에서 호출하지 말 것.
     */
    protected Like() {
    }

    public Like(Study study, MemberId memberId) {
        this.UUID = createUUID();
        this.study = study;
        this.memberId = memberId;
        this.clicked = LikeClicked.TRUE;
    }

    public static Like update(Study study, MemberId memberId) {
        return new Like(study, memberId);
    }

    private UUID createUUID() {
        return Generators.timeBasedGenerator().generate();
    }

    public UUID getLikeId() {
        return UUID;
    }

    public Study getStudy() {
        return study;
    }

    public MemberId getMemberId() {
        return memberId;
    }

    public LikeClicked getClicked() {
        return clicked;
    }

    /**
     * Like는 PK가 아닌 Study와 MemberId로 구분
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Like)) return false;
        Like like = (Like) o;
        return getStudy().equals(like.getStudy()) && getMemberId().equals(like.getMemberId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getStudy(), getMemberId());
    }

    @Override
    public String toString() {
        return String.format("아이디: %s, 스터디 아이디: %s, 회원 아이디: %s", UUID, study, memberId);
    }
}
