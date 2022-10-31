package com.example.backend.business.core.member.entity.values;

import javax.persistence.Embeddable;
import java.util.Objects;

@Embeddable
public class CurrentLoginIpAddress {

    private String currentLoginIpAddress;

    /**
     * @Nullary-Constructor. JPA 기본 생성자로 member 외부 패키지에서 호출하지 말 것.
     */
    public CurrentLoginIpAddress() {
    }

    private CurrentLoginIpAddress(String currentLoginIpAddress) {
        validateCurrentLoginIpAddress(currentLoginIpAddress);
        this.currentLoginIpAddress = currentLoginIpAddress;
    }

    private void validateCurrentLoginIpAddress(String currentLoginIpAddress) {
        if (Objects.isNull(currentLoginIpAddress)) {
            throw new IllegalArgumentException("올바르지 않은 IP 주소입니다.");
        }
    }

    public static CurrentLoginIpAddress from(String currentLoginIpAddress) {
        return new CurrentLoginIpAddress(currentLoginIpAddress);
    }

    public String getCurrentLoginIpAddress() {
        return currentLoginIpAddress;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CurrentLoginIpAddress)) return false;
        CurrentLoginIpAddress that = (CurrentLoginIpAddress) o;
        return currentLoginIpAddress.equals(that.currentLoginIpAddress);
    }

    @Override
    public int hashCode() {
        return Objects.hash(currentLoginIpAddress);
    }
}
