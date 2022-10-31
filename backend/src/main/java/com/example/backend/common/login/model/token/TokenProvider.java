package com.example.backend.common.login.model.token;

public interface TokenProvider {

    String renewAccessToken(Long memberId);
}
