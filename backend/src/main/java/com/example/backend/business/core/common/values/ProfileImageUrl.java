package com.example.backend.business.core.common.values;

import com.example.backend.business.core.common.ErrorField;

import javax.persistence.Embeddable;
import java.util.Objects;

@Embeddable
public class ProfileImageUrl {

    private static final int MAX_PROFILE_IMAGE_URL_LENGTH = 1000;

    private String profileImageUrl;

    /**
     * @Nullary-Constructor. JPA 기본 생성자로 domain 외부 패키지에서 호출하지 말 것.
     */
    protected ProfileImageUrl() {
    }

    private ProfileImageUrl(String profileImageUrl) {
        validateProfileImageUrl(profileImageUrl);
        this.profileImageUrl = profileImageUrl;
    }

    private void validateProfileImageUrl(String profileImageUrl) {
        if (Objects.isNull(profileImageUrl)) {
            throw new IllegalArgumentException("프로필 이미지가 존재하지 않습니다.", ErrorField.of("profileImageUrl", profileImageUrl));
        }
        if (profileImageUrl.length() > MAX_PROFILE_IMAGE_URL_LENGTH) {
            throw new IllegalArgumentException("프로필 이미지 최대 입력길이를 초과했습니다.", ErrorField.of("profileImageUrl", profileImageUrl));
        }
    }

    public static ProfileImageUrl from(String githubId) {
        return new ProfileImageUrl(githubId);
    }

    public String getProfileImageUrl() {
        return profileImageUrl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProfileImageUrl)) return false;
        ProfileImageUrl that = (ProfileImageUrl) o;
        return getProfileImageUrl().equals(that.getProfileImageUrl());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getProfileImageUrl());
    }

    @Override
    public String toString() {
        return profileImageUrl;
    }
}
