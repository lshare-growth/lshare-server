package com.example.backend.business.core.member.entity.values;

import com.example.backend.business.core.common.ErrorField;

import javax.persistence.Embeddable;
import java.util.Objects;

@Embeddable
public class GithubId {

    private static final int MAX_GITHUBID_LENGTH = 39;

    private String githubId;

    /**
     * @Nullary-Constructor. JPA 기본 생성자로 member 외부 패키지에서 호출하지 말 것.
     */
    protected GithubId() {
    }

    public GithubId(String githubId) {
        validateGithubId(githubId);
        this.githubId = githubId;
    }

    private void validateGithubId(String githubId) {
        if (Objects.isNull(githubId) || githubId.isBlank()) {
            throw new IllegalArgumentException("깃허브 아이디를 입력해주세요.", ErrorField.of("githubId", githubId));
        }
        if (githubId.length() > MAX_GITHUBID_LENGTH) {
            throw new IllegalArgumentException("아이디 최대 입력길이를 초과했습니다.", ErrorField.of("githubId", githubId));
        }
    }

    public static GithubId from(String githubId) {
        return new GithubId(githubId);
    }

    public String getGithubId() {
        return githubId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GithubId)) return false;
        GithubId githubId1 = (GithubId) o;
        return getGithubId().equals(githubId1.getGithubId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getGithubId());
    }

    @Override
    public String toString() {
        return githubId;
    }
}
