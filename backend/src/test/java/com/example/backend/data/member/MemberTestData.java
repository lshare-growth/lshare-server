package com.example.backend.data.member;

import com.example.backend.business.core.member.entity.Member;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.Objects;

public class MemberTestData {

    public static final Long MEMBER_ID = 1L;

    public static Member createMemberTestData() {
        return new Member("devjun10", "https://iconvulture.com/wp-content/uploads/2017/12/github-character.png");
    }

    public static Member createMemberTestDataWithId() {
        Member newMember = new Member("devjun10", "https://iconvulture.com/wp-content/uploads/2017/12/github-character.png");
        Field idField = ReflectionUtils.findField(Member.class, "memberId");
        ReflectionUtils.makeAccessible(Objects.requireNonNull(idField));
        ReflectionUtils.setField(Objects.requireNonNull(idField), newMember, MEMBER_ID);
        return newMember;
    }
}
