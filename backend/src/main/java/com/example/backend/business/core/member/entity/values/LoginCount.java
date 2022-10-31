package com.example.backend.business.core.member.entity.values;

import javax.persistence.Embeddable;
import java.util.Objects;

@Embeddable
public class LoginCount {

    private static final int INITIALIZATION = 1;

    private int loginCount;

    /**
     * @Nullary-Constructor. JPA 기본 생성자로 member 외부 패키지에서 호출하지 말 것.
     */
    protected LoginCount() {
        this.loginCount = INITIALIZATION;
    }

    public LoginCount(int loginCount) {
        this.loginCount = loginCount;
    }

    public LoginCount increaseAndGetLoginCount() {
        return new LoginCount(loginCount);
    }

    public static LoginCount initLoginCount() {
        return new LoginCount();
    }

    public int getLoginCount() {
        return loginCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LoginCount)) return false;
        LoginCount that = (LoginCount) o;
        return loginCount == that.loginCount;
    }

    @Override
    public int hashCode() {
        return Objects.hash(loginCount);
    }

    @Override
    public String toString() {
        return String.valueOf(loginCount);
    }
}
