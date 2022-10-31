package com.example.backend.business.core.image.entity.vo;

import javax.persistence.Embeddable;
import java.util.Objects;

@Embeddable
public class ImageUrl {

    private String imageUrl;

    /**
     * @Nullary-Constructor. JPA 기본 생성자로 study 외부 패키지에서 호출하지 말 것.
     */
    protected ImageUrl() {
    }

    public ImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public static ImageUrl from(String imageUrl) {
        return new ImageUrl(imageUrl);
    }

    public String getImageUrl() {
        return imageUrl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ImageUrl)) return false;
        ImageUrl imageUrl1 = (ImageUrl) o;
        return getImageUrl().equals(imageUrl1.getImageUrl());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getImageUrl());
    }

    @Override
    public String toString() {
        return imageUrl;
    }
}
