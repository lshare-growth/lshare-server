package com.example.backend.business.core.like.entity.values;

import com.example.backend.business.core.like.entity.Like;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.OneToMany;
import java.util.LinkedHashSet;
import java.util.Set;

@Embeddable
public class Likes {

    @OneToMany(mappedBy = "study", cascade = {CascadeType.ALL}, orphanRemoval = true)
    private Set<Like> likes;

    /**
     * @Nullary-Constructor. JPA 기본 생성자로 study 외부 패키지에서 호출하지 말 것.
     */
    protected Likes() {
        this(new LinkedHashSet<>());
    }

    public static Likes initLikes() {
        return new Likes();
    }

    public Likes(Set<Like> likes) {
        this.likes = likes;
    }

    public boolean contains(Like like) {
        return this.likes.contains(like);
    }

    @Override
    public String toString() {
        return likes.toString();
    }
}
