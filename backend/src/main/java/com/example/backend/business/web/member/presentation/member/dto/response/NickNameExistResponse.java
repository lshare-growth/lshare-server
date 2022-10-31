package com.example.backend.business.web.member.presentation.member.dto.response;

public class NickNameExistResponse {

    private boolean nickNameExistence;

    private NickNameExistResponse(Integer value) {
        this.nickNameExistence = value != null;
    }

    public static NickNameExistResponse of(Integer value) {
        return new NickNameExistResponse(value);
    }

    public boolean isNickNameExistence() {
        return nickNameExistence;
    }

    @Override
    public String toString() {
        return String.format("중복된 닉네임 여부: %s", nickNameExistence);
    }
}
