package com.example.backend.business.core.member.entity;

import com.example.backend.business.core.common.values.CreatedAt;
import com.fasterxml.uuid.Generators;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.Objects;

@Entity
public class Follow {

    @Id
    @Column(columnDefinition = "BINARY(16)")
    private java.util.UUID UUID;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "source_id")
    private Member source;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "target_id")
    private Member target;

    @Embedded
    private CreatedAt createdAt;

    /**
     * @Nullary-Constructor. JPA 기본 생성자로 member 외부 패키지에서 호출하지 말 것.
     */
    protected Follow() {
    }

    public Follow(Member source, Member target) {
        validateFollow(source, target);
        this.UUID = createUUID();
        this.source = source;
        this.target = target;
        this.createdAt = CreatedAt.initCreatedAt();
    }

    private void validateFollow(Member source, Member target) {
        if (source.equals(target)) {
            throw new IllegalStateException();
        }
    }

    private java.util.UUID createUUID() {
        return Generators.timeBasedGenerator().generate();
    }

    public java.util.UUID getUUID() {
        return UUID;
    }

    public Member getSource() {
        return source;
    }

    public Member getTarget() {
        return target;
    }

    public CreatedAt getCreatedAt() {
        return createdAt;
    }

    public boolean isFollowing(Member target) {
        return this.target.equals(target);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Follow)) return false;
        Follow follow = (Follow) o;
        return getSource().equals(follow.getSource()) && getTarget().equals(follow.getTarget());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getSource(), getTarget());
    }

    @Override
    public String toString() {
        return UUID.toString();
    }
}
