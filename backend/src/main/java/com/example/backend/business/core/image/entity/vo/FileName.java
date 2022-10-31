package com.example.backend.business.core.image.entity.vo;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import java.util.Objects;
import java.util.UUID;

@Embeddable
public class FileName {

    @Embedded
    private OriginalFileName originalFileName;

    /**
     * @Nullary-Constructor. JPA 기본 생성자로 domain 패키지 외부에서 호출하지 말 것.
     */
    protected FileName() {
    }

    public FileName(String originalFileName) {
        validateFileName(originalFileName);
        this.originalFileName = OriginalFileName.from(originalFileName);
//        this.fileName = FileName.initFileName();
    }

    private void validateFileName(final String originalFileName) {
        Objects.requireNonNull(originalFileName, "파일 이름이 존재하지 않습니다.");
    }

    public static FileName from(final String fileName) {
        return new FileName(fileName);
    }

    private String createUUID() {
        return UUID.randomUUID().toString();
    }

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (!(o instanceof FileName)) return false;
//        FileName fileName1 = (FileName) o;
//        return fileName.equals(fileName1.fileName);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(fileName);
//    }

//    @Override
//    public String toString() {
//        return fileName.toString();
//    }
}
