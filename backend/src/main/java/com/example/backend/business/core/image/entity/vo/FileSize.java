package com.example.backend.business.core.image.entity.vo;

import javax.persistence.Embeddable;
import java.util.Objects;

@Embeddable
public class FileSize {

    private static final long MAX_FILE_SIZE = 10_000_000; // 10MB

    private long fileSize;

    /**
     * @Nullary-Constructor. JPA 기본 생성자로 domain 외부 패키지에서 호출하지 말 것.
     */
    protected FileSize() {
    }

    public FileSize(long fileSize) {
        validateFileSize(fileSize);
        this.fileSize = fileSize;
    }

    private void validateFileSize(long fileSize) {
        if (fileSize == 0) {
            throw new IllegalArgumentException("파일이 존재하지 않습니다.");
        }
        if (fileSize > MAX_FILE_SIZE) {
            throw new IllegalArgumentException("최대 파일 크기를 초과했습니다.");
        }
    }

    public static FileSize from(long fileSize) {
        return new FileSize(fileSize);
    }

    public long getFileSize() {
        return fileSize;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FileSize)) return false;
        FileSize fileSize1 = (FileSize) o;
        return getFileSize() == fileSize1.getFileSize();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getFileSize());
    }

    @Override
    public String toString() {
        return String.valueOf(fileSize);
    }
}
