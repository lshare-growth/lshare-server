package com.example.backend.business.core.common.values;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import java.time.LocalDateTime;
import java.util.Objects;

@Embeddable
public class DateTime {

    @Embedded
    private final CreatedAt createdAt;

    @Embedded
    private LastModifiedAt lastModifiedAt;

    /**
     * @Nullary-Constructor. JPA 기본 생성자로 domain 패키지 외부에서 호출하지 말 것.
     */
    protected DateTime() {
        this.createdAt = CreatedAt.initCreatedAt();
        this.lastModifiedAt = LastModifiedAt.initLastModifiedAt();
    }

    public static DateTime initDateTime() {
        return new DateTime();
    }

    public LocalDateTime getCreatedAt() {
        return createdAt.getCreatedAt();
    }

    public LocalDateTime getLastModifiedAt() {
        return lastModifiedAt == null ? createdAt.getCreatedAt() : lastModifiedAt.getLastModifiedAt();
    }

    public void update() {
        this.lastModifiedAt = LastModifiedAt.update();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DateTime)) return false;
        DateTime dateTime = (DateTime) o;
        return getCreatedAt().equals(dateTime.getCreatedAt()) && getLastModifiedAt().equals(dateTime.getLastModifiedAt());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCreatedAt(), getLastModifiedAt());
    }

    @Override
    public String toString() {
        return String.format("생성일: %s, 마지막 수정일: %s", createdAt, lastModifiedAt);
    }
}
