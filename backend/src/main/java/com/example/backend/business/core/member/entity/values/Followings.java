package com.example.backend.business.core.member.entity.values;

import com.example.backend.business.core.member.entity.Follow;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.OneToMany;
import java.util.LinkedList;
import java.util.List;

@Embeddable
public class Followings {

    @OneToMany(mappedBy = "source", cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, orphanRemoval = true)
    private List<Follow> followings;

    /**
     * @Nullary-Constructor. JPA 기본 생성자로 member 외부 패키지에서 호출하지 말 것.
     */
    protected Followings() {
        this(new LinkedList<>());
    }

    public Followings(List<Follow> followings) {
        this.followings = followings;
    }

    public List<Follow> getFollowings() {
        return followings;
    }

    public void add(Follow follow) {
        followings.add(follow);
    }

    public int size() {
        return this.followings.size();
    }

    @Override
    public String toString() {
        return String.format("팔로잉중인 대상 목록: %s", followings);
    }
}
