package com.example.backend.business.core.image.entity.vo;

import com.example.backend.business.core.image.entity.Image;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Embeddable
public class Images {

    @OneToMany(mappedBy = "study", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private List<Image> images;

    /**
     * @Nullary-Constructor. JPA 기본 생성자로 study 외부 패키지에서 호출하지 말 것.
     */
    protected Images() {
        this(Collections.emptyList());
    }

    public Images(List<Image> images) {
        this.images = images;
    }

    public List<Image> getImages() {
        return new ArrayList<>(images);
    }

    @Override
    public String toString() {
        return images.toString();
    }
}
