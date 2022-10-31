package com.example.backend.common.login.model.token;

public interface TokenValidator {

    boolean validate(String token);
}
