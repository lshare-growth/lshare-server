package com.example.backend.business.core.member.entity.values;

import com.example.backend.business.core.member.entity.Follow;
import com.example.backend.business.core.member.entity.Member;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.OneToMany;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Embeddable
public class Followers {

    @OneToMany(mappedBy = "target", cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, orphanRemoval = true)
    private List<Follow> followers;

    /**
     * @Nullary-Constructor. JPA 기본 생성자로 member 외부 패키지에서 호출하지 말 것.
     */
    protected Followers() {
        this.followers = new LinkedList<>();
    }

    private Followers(List<Follow> followers) {
        this.followers = followers;
    }

    public static Followers from(List<Follow> followers) {
        return new Followers(followers);
    }

    public List<Follow> getFollowers() {
        return followers;
    }

    public int size() {
        return this.followers.size();
    }

    public boolean hasNext() {
        if (this.followers.size() > 5) {
            this.followers.remove(this.followers.size() - 1);
            return true;
        }
        return false;
    }

    public List<Long> getTargetIds() {
        return this.followers.stream()
                .map(Follow::getTarget)
                .map(Member::getMemberId)
                .collect(Collectors.toUnmodifiableList());
    }

    public List<Long> getSourceIds() {
        return this.followers.stream()
                .map(Follow::getSource)
                .map(Member::getMemberId)
                .collect(Collectors.toUnmodifiableList());
    }

    @Override
    public String toString() {
        return String.format("팔로워 목록: %s", followers);
    }
}
