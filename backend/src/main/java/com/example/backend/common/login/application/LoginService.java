package com.example.backend.common.login.application;

import com.example.backend.business.core.member.entity.Member;
import com.example.backend.business.core.notification.entity.NotificationMessageReadStatus;
import com.example.backend.common.login.model.token.OauthClient;
import com.example.backend.common.login.model.token.jwt.JwtToken;

public interface LoginService {
    Member save(OauthClient oauthClient);

    void saveToken(String key, String value);

    JwtToken createToken(Member member, NotificationMessageReadStatus messageReadStatus);
}
