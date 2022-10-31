package com.example.backend.business.core.tag.entity;

import com.example.backend.business.core.tag.entity.values.HashTagId;
import com.example.backend.business.core.tag.entity.values.TagName;
import com.fasterxml.uuid.Generators;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class TopSearchedHashTag {

    @Id
    @Column(columnDefinition = "BINARY(16)")
    private java.util.UUID UUID;

    @Embedded
    private TagName tagName;

    @Embedded
    private HashTagId hashTagId;

    /**
     * @Nullary-Constructor. JPA 기본 생성자로 tag 외부 패키지에서 호출하지 말 것.
     */
    protected TopSearchedHashTag() {
        this.UUID = createUUID();
    }

    public TopSearchedHashTag(TagName tagName, Long hashTagId) {
        this.UUID = createUUID();
        this.tagName = tagName;
        this.hashTagId = HashTagId.from(hashTagId);
    }

    private java.util.UUID createUUID() {
        return Generators.timeBasedGenerator().generate();
    }

    public String getUUID() {
        return UUID.toString();
    }

    public String getTagName() {
        return tagName.getTagName();
    }

    public Long getHashTagId() {
        return hashTagId.getHashTagId();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TopSearchedHashTag)) return false;
        TopSearchedHashTag that = (TopSearchedHashTag) o;
        return getUUID().equals(that.getUUID());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUUID());
    }

    @Override
    public String toString() {
        return String.format("태그명= %s", tagName);
    }
}
