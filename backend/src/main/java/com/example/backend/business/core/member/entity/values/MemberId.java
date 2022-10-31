package com.example.backend.business.core.member.entity.values;

import com.example.backend.common.exception.BusinessException;

import javax.persistence.Embeddable;
import java.util.Objects;

import static com.example.backend.common.exception.member.MemberTypeException.UNAUTHORIZED_EXCEPTION;

@Embeddable
public class MemberId {

    private Long memberId;

    /**
     * @Nullary-Constructor. JPA 기본 생성자로 Member 외부 패키지에서 호출하지 말 것.
     */
    protected MemberId() {
    }

    private MemberId(Long memberId) {
        validateMemberId(memberId);
        this.memberId = memberId;
    }

    private void validateMemberId(Long memberId) {
        if (Objects.isNull(memberId)) {
            throw new BusinessException(UNAUTHORIZED_EXCEPTION);
        }
    }

    private MemberId(String memberId) {
        validateMemberId(memberId);
        this.memberId = parseMemberId(memberId);
    }

    private void validateMemberId(String memberId) {
        if (Objects.isNull(memberId)) {
            throw new BusinessException(UNAUTHORIZED_EXCEPTION);
        }
    }

    private MemberId(Object memberId) {
        validateMemberId(memberId);
        this.memberId = parseMemberId(memberId);
    }

    private void validateMemberId(Object memberId) {
        if (Objects.isNull(memberId)) {
            throw new BusinessException(UNAUTHORIZED_EXCEPTION);
        }
    }

    private Long parseMemberId(String memberId) {
        if (Objects.isNull(memberId)) {
            return null;
        }
        return Long.parseLong(memberId);
    }

    private Long parseMemberId(Object memberId) {
        if (Objects.isNull(memberId)) {
            return null;
        }
        if (memberId instanceof String) {
            String parsedMemberId = (String) memberId;
            return Long.parseLong(parsedMemberId);
        }
        return (Long) memberId;
    }

    public static void validateMemberId(Long receivedMemberId, Long memberId) {
        if (Objects.isNull(receivedMemberId) || Objects.isNull(memberId)) {
            throw new IllegalArgumentException("회원 아이디가 존재하지 않습니다.");
        }
        if (!receivedMemberId.equals(memberId)) {
            throw new BusinessException(UNAUTHORIZED_EXCEPTION);
        }
    }

    public static void validateMemberId(Long receivedMemberId, MemberId memberId) {
        if (Objects.isNull(receivedMemberId) || Objects.isNull(memberId)) {
            throw new IllegalArgumentException("회원 아이디가 존재하지 않습니다.");
        }
        if (!receivedMemberId.equals(memberId.getMemberId())) {
            throw new BusinessException(UNAUTHORIZED_EXCEPTION);
        }
    }

    public static MemberId from(Long memberId) {
        return new MemberId(memberId);
    }

    public static MemberId from(Object memberId) {
        return new MemberId(memberId);
    }

    public static MemberId getInvalidMemberId() {
        return MemberId.from(-1L);
    }

    public Long getMemberId() {
        return memberId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MemberId)) return false;
        MemberId memberId1 = (MemberId) o;
        return memberId.equals(memberId1.memberId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(memberId);
    }

    @Override
    public String toString() {
        return memberId.toString();
    }
}
