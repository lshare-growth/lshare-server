package com.example.backend.lshare.business.integration.follow;

import com.example.backend.business.core.member.entity.FollowHistory;
import com.example.backend.business.core.member.entity.Member;
import com.example.backend.business.web.member.application.follow.FollowCommandService;
import com.example.backend.data.member.MemberTestData;
import com.example.backend.lshare.IntegrationTestContainer;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("팔로우 통합 테스트")
class FollowIntegrationTest extends IntegrationTestContainer {

    @Autowired
    private FollowCommandService followCommandService;

    @Nested
    @DisplayName("팔로우/언팔로우를 할때")
    class NestedTest {

        @Test
        @DisplayName("팔로우를 한 적이 없다면 target이 팔로우 된다.")
        void 팔로우_성공_통합_테스트() {
            // given
            Member source = memberCommandService.save(MemberTestData.createMemberTestData());
            Member target = memberCommandService.save(MemberTestData.createMemberTestData());

            // when
            followCommandService.updateFollow(FollowHistory.NON_EXIST, source, target);

            // then
            assertAll(
                    () -> assertEquals(1, source.getFollowingCount()),
                    () -> assertEquals(1, target.getFollowerCount())
            );
        }

        @Test
        @DisplayName("팔로우를 한 적이 있다면 target이 언팔로우 된다.")
        void 언팔로우_통합_테스트() {
            // given
            Member source = memberCommandService.save(MemberTestData.createMemberTestData());
            Member target = memberCommandService.save(MemberTestData.createMemberTestData());
            followCommandService.updateFollow(FollowHistory.NON_EXIST, source, target);

            // when
            followCommandService.updateFollow(FollowHistory.EXIST, source, target);

            // then
            assertAll(
                    () -> assertEquals(0, source.getFollowingCount()),
                    () -> assertEquals(0, target.getFollowerCount())
            );
        }
    }
}
