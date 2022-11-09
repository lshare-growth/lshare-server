package com.example.backend.business.core.tag.entity.values;

import com.example.backend.business.core.common.ErrorField;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class HashTagId implements Serializable {

    private Long hashTagId;

    /**
     * @Nullary-Constructor. JPA 기본 생성자로 tag 외부 패키지에서 호출하지 말 것.
     */
    protected HashTagId() {
    }

    private HashTagId(Long hashTagId) {
        validateHashTagId(hashTagId);
        this.hashTagId = hashTagId;
    }

    public static HashTagId from(Long hashTagId) {
        return new HashTagId(hashTagId);
    }

    private void validateHashTagId(Long hashTagId) {
        if (Objects.isNull(hashTagId)) {
            throw new IllegalArgumentException("해시태그 아이디가 존재하지 않습니다.", ErrorField.of("hashTagId", hashTagId));
        }
    }

    public Long getHashTagId() {
        return hashTagId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof HashTagId)) return false;
        HashTagId hashTagId1 = (HashTagId) o;
        return hashTagId.equals(hashTagId1.hashTagId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(hashTagId);
    }

    @Override
    public String toString() {
        return hashTagId.toString();
    }
}
