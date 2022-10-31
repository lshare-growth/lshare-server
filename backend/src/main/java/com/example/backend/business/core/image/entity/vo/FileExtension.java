package com.example.backend.business.core.image.entity.vo;

import javax.persistence.Embeddable;
import java.util.List;
import java.util.Objects;

@Embeddable
public class FileExtension {

    private static final List<String> extensions = List.of("jpg", "jpeg", "png", "gif");

    private String extension;

    /**
     * @Nullary-Constructor. JPA 기본 생성자로 domain 패키지 외부에서 호출하지 말 것.
     */
    protected FileExtension() {
    }

    public FileExtension(String extension) {
        validateExtension(extension);
        this.extension = parseExtention(extension);
    }

    private void validateExtension(String fileName) {
        if (Objects.isNull(fileName)) {
            throw new IllegalArgumentException("파일 형식이 존재하지 않습니다.");
        }
        if (!extensions.contains(parseExtention(fileName))) {
            throw new IllegalArgumentException("지원되지 않는 형식의 파일입니다.");
        }
    }

    public static FileExtension from(String fileName) {
        return new FileExtension(fileName);
    }

    private String parseExtention(String fileName) {
        String format = fileName.substring(fileName.lastIndexOf("."));
        return format.substring(1);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FileExtension)) return false;
        FileExtension fileExtension1 = (FileExtension) o;
        return extension.equals(fileExtension1.extension);
    }

    @Override
    public int hashCode() {
        return Objects.hash(extension);
    }

    @Override
    public String toString() {
        return extension;
    }
}
