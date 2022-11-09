package com.example.backend.business.core.member.entity.values;

import com.example.backend.business.core.common.ErrorField;

import javax.persistence.Embeddable;
import java.util.Objects;

@Embeddable
public class NickName {

    private String nickName;

    /**
     * @Nullary-Constructor. JPA 기본 생성자로 member 외부 패키지에서 호출하지 말 것.
     */
    protected NickName() {
    }

    private NickName(String nickName) {
        validateName(nickName);
        this.nickName = nickName;
    }

    private void validateName(String nickName) {
        if (Objects.isNull(nickName) || nickName.isBlank()) {
            throw new IllegalArgumentException("회원 이름은 공백일 수 없습니다.", ErrorField.of("nickName", nickName));
        }
        if (nickName.length() > 39) {
            throw new IllegalArgumentException("입력 가능한 이름의 최대길이를 초과했습니다.", ErrorField.of("nickName", nickName));
        }

        String blankDeletedNickName = nickName.replaceAll(" ", "");

        if (!blankDeletedNickName.equals(nickName)) {
            throw new IllegalArgumentException("닉네임에 공백은 존재할 수 없습니다.", ErrorField.of("nickName", nickName));
        }
    }

    public static NickName from(String name) {
        return new NickName(name);
    }

    public String getNickName() {
        return nickName;
    }

    public void updateNickName(NickName nickName) {
        if (this.equals(nickName)) {
            return;
        }
        this.nickName = nickName.getNickName();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof NickName)) return false;
        NickName nickName1 = (NickName) o;
        return getNickName().equals(nickName1.getNickName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getNickName());
    }

    @Override
    public String toString() {
        return nickName;
    }
}
