package com.example.backend.business.core.image.entity.vo;

import com.fasterxml.uuid.Generators;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.util.Objects;
import java.util.UUID;

@Embeddable
public class FileNameUUID {

    @Column(columnDefinition = "BINARY(16)")
    private UUID fileNameUUID;

    /**
     * @Nullary-Constructor. JPA 기본 생성자로 domain 패키지 외부에서 호출하지 말 것.
     */
    protected FileNameUUID() {
        this.fileNameUUID = createUUID();
    }

    private UUID createUUID() {
        return Generators.timeBasedGenerator().generate();
    }

    public static FileNameUUID initFileNameUUID() {
        return new FileNameUUID();
    }

    public String getFileNameUUID() {
        return fileNameUUID.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FileNameUUID)) return false;
        FileNameUUID that = (FileNameUUID) o;
        return getFileNameUUID().equals(that.getFileNameUUID());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getFileNameUUID());
    }

    @Override
    public String toString() {
        return fileNameUUID.toString();
    }
}
