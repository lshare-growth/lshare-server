package com.example.backend.business.core.member.entity.values;

import javax.persistence.Embeddable;
import java.util.Objects;

@Embeddable
public class LastLoginIpAddress {

    private String lastLoginIpAddress;

    protected LastLoginIpAddress() {
    }

    private LastLoginIpAddress(String lastLoginIpAddress) {
        validateLastLoginIpAddress(lastLoginIpAddress);
        this.lastLoginIpAddress = lastLoginIpAddress;
    }

    private void validateLastLoginIpAddress(String lastLoginIpAddress) {
        if (Objects.isNull(lastLoginIpAddress)) {
            throw new IllegalArgumentException("올바르지 않은 IP 주소입니다.");
        }
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
