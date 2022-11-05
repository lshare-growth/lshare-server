package com.example.backend.business.core.tag.entity;

import com.example.backend.business.core.tag.entity.values.HashTagId;
import com.example.backend.business.core.tag.entity.values.TagCount;
import com.example.backend.business.core.tag.entity.values.TagName;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class HashTag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long hashTagId;

    @Embedded
    private TagName tagName;

    @Embedded
    private TagCount tagCount;

    /**
     * @Nullary-Constructor. JPA 기본 생성자로 tag 외부 패키지에서 호출하지 말 것.
     */
    protected HashTag() {
    }

    private HashTag(TagName tagName) {
        this.tagName = tagName;
        this.tagCount = TagCount.initTagCount();
    }

    public static HashTag createHashTag(TagName tagName) {
        return new HashTag(tagName);
    }

    public Long getHashTagId() {
        return hashTagId;
    }

    public TagName getTagNameAsValue() {
        return tagName;
    }

    public String getTagName() {
        return tagName.getTagName();
    }

    public String getTagNameAsString() {
        return tagName.getTagName();
    }

    public TagCount getTagCount() {
        return tagCount;
    }

    public void plusTagCount() {
        this.tagCount.increaseTagCount();
    }

    public HashTagId getHashTagIdAsValue() {
        return HashTagId.from(hashTagId);
    }

    /**
     * HashTag는 PK가 아닌 태그명으로 구분
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof HashTag)) return false;
        HashTag hashTag = (HashTag) o;
        return tagName.equals(hashTag.tagName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tagName);
    }

    @Override
    public String toString() {
        return tagName.toString();
    }
}
