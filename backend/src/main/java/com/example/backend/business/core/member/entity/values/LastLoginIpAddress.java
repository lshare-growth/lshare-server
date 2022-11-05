package com.example.backend.business.core.member.entity.values;

import javax.persistence.Embeddable;
import java.util.Objects;

@Embeddable
public class LastLoginIpAddress {

    private String lastLoginIpAddress;

    /**
     * @Nullary-Constructor. JPA 기본 생성자로 member 외부 패키지에서 호출하지 말 것.
     */
    protected LastLoginIpAddress() {
    }

    private LastLoginIpAddress(String lastLoginIpAddress) {
        this.lastLoginIpAddress = extractIpAddress(lastLoginIpAddress);
    }

    private String extractIpAddress(String lastLoginIpAddress) {
        if (Objects.isNull(lastLoginIpAddress) || lastLoginIpAddress.equals("")) {
            return "UN_KNOWN";
        }
        return lastLoginIpAddress;
    }

    public static LastLoginIpAddress from(String lastLoginIpAddress) {
        return new LastLoginIpAddress(lastLoginIpAddress);
    }

    public String getLastLoginIpAddress() {
        return lastLoginIpAddress;
    }

    public void validateIpAddress(String currentLoginIpAddress) {
        if (!Objects.isNull(lastLoginIpAddress) && !isSameIpAddress(currentLoginIpAddress)) {
            throw new IllegalArgumentException("다른 주소에서 로그인했습니다.");
        }
    }

    private boolean isSameIpAddress(String currentLoginIpAddress) {
        return this.lastLoginIpAddress.equals(currentLoginIpAddress);
    }

    public LastLoginIpAddress updateIpAddress(String currentLoginIpAddress) {
        return new LastLoginIpAddress(currentLoginIpAddress);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LastLoginIpAddress)) return false;
        LastLoginIpAddress that = (LastLoginIpAddress) o;
        return getLastLoginIpAddress().equals(that.getLastLoginIpAddress());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getLastLoginIpAddress());
    }

    @Override
    public String toString() {
        return lastLoginIpAddress;
    }
}
