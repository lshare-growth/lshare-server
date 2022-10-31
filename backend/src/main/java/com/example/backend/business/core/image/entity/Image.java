package com.example.backend.business.core.image.entity;

import com.example.backend.business.core.common.Deleted;
import com.example.backend.business.core.image.entity.vo.FileExtension;
import com.example.backend.business.core.image.entity.vo.FileNameUUID;
import com.example.backend.business.core.image.entity.vo.FileSize;
import com.example.backend.business.core.image.entity.vo.ImageUrl;
import com.example.backend.business.core.image.entity.vo.OriginalFileName;
import com.example.backend.business.core.study.entity.Study;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.Objects;

@Entity
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long studyImageId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "study_id")
    private Study study;

    @Embedded
    private FileNameUUID fileNameUUID;

    @Embedded
    private OriginalFileName originalFileName;

    @Embedded
    private ImageUrl imageUrl;

    @Embedded
    private FileExtension fileExtension;

    @Embedded
    private FileSize fileSize;

    @Column(columnDefinition = "ENUM('TRUE', 'FALSE')")
    @Enumerated(EnumType.STRING)
    private Deleted deleted;

    /**
     * @Nullary-Constructor. JPA 기본 생성자로 study 외부 패키지에서 호출하지 말 것.
     */
    protected Image() {
    }

    public Image(OriginalFileName originalFileName){
        this.originalFileName = originalFileName;
    }

    public Long getStudyImageId() {
        return studyImageId;
    }

    public String getImageUrl() {
        return imageUrl.getImageUrl();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Image)) return false;
        Image image = (Image) o;
        return getStudyImageId().equals(image.getStudyImageId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getStudyImageId());
    }

    @Override
    public String toString() {
        return studyImageId.toString();
    }
}
