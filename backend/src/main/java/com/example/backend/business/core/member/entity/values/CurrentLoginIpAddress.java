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
        this.currentLoginIpAddress = extractIpAddress(currentLoginIpAddress);
    }

    private String extractIpAddress(String lastLoginIpAddress) {
        if (Objects.isNull(lastLoginIpAddress) || lastLoginIpAddress.equals("")) {
            return "UN_KNOWN";
        }
        return lastLoginIpAddress;
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

    @Override
    public String toString() {
        return currentLoginIpAddress;
    }
}
