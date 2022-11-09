package com.example.backend.business.core.member.entity.values;

import com.example.backend.business.core.common.ErrorField;

import javax.persistence.Embeddable;
import java.util.Objects;

@Embeddable
public class GithubLink {

    private static final String GITHUB_FIXED_URL = "https://github.com/";

    private String githubLink;

    /**
     * @Nullary-Constructor. JPA 기본 생성자로 member 외부 패키지에서 호출하지 말 것.
     */
    protected GithubLink() {
    }

    private GithubLink(GithubId githubId) {
        this.githubLink = convertToGithubURL(githubId);
    }

    private String convertToGithubURL(GithubId githubId) {
        String githubLink = GITHUB_FIXED_URL + githubId.getGithubId();
        validateGithubLink(githubLink);
        return githubLink;
    }

    private void validateGithubLink(String githubLink) {
        if (githubLink.length() > 60) {
            throw new IllegalArgumentException("올바르지 않은 깃허브 링크입니다.", ErrorField.of("githubLink", githubLink));
        }
    }

    public static GithubLink from(GithubId githubId) {
        return new GithubLink(githubId);
    }

    public String getGithubLink() {
        return githubLink;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GithubLink)) return false;
        GithubLink that = (GithubLink) o;
        return githubLink.equals(that.githubLink);
    }

    @Override
    public int hashCode() {
        return Objects.hash(githubLink);
    }

    @Override
    public String toString() {
        return githubLink;
    }
}
