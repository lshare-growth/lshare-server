package com.example.backend.lshare.business.integration.member;

import com.example.backend.business.core.common.Deleted;
import com.example.backend.business.core.common.District;
import com.example.backend.business.core.member.entity.Member;
import com.example.backend.business.core.member.entity.values.BirthDate;
import com.example.backend.business.core.member.entity.values.BlogUrl;
import com.example.backend.business.core.member.entity.values.Introduction;
import com.example.backend.business.core.member.entity.values.NickName;
import com.example.backend.business.web.member.application.member.MemberCommandService;
import com.example.backend.data.member.MemberTestData;
import com.example.backend.lshare.IntegrationTestContainer;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("회원 통합 테스트")
public class MemberIntegrationTest extends IntegrationTestContainer {

    @Autowired
    private MemberCommandService memberCommandService;

    @org.junit.jupiter.api.Nested
    @DisplayName("스터디를 삭제할때")
    class NestedTest {

        @Test
        @DisplayName("내용을 빠트리지 않고 올바르게 작성했다면 스터디가 생성된다.")
        void 회원정보수정_성공_통합_테스트() {
            // given
            Member newMember = memberCommandService.save(MemberTestData.createMemberTestData());

            // when
            memberCommandService.updatNickName(newMember, NickName.from("HelloWorld"));

            // then
            assertEquals(NickName.from("HelloWorld"), newMember.getNickNameAsValue());
        }

        @Test
        @DisplayName("내용을 빠트리지 않고 올바르게 작성했다면 스터디가 생성된다.")
        void study_create_success_t3est() {
            // given
            Member newMember = memberCommandService.save(MemberTestData.createMemberTestData());

            // when
            memberCommandService.delete(newMember);

            // then
            assertEquals(Deleted.TRUE, newMember.getDeleted());
        }

        @Test
        @DisplayName("내용을 빠트리지 않고 올바르게 작성했다면 스터디가 생성된다.")
        void 스터디개설_성공_통합_테스트() {
            // given
            Member newMember = memberCommandService.save(MemberTestData.createMemberTestData());
            BlogUrl blogUrl = BlogUrl.from("www.tistory.com");
            District district = District.BUSAN;
            BirthDate birthDate = BirthDate.from(LocalDate.of(2011, 10, 10));
            Introduction introduction = Introduction.from("반갑습니다.");

            // when
            memberCommandService.updateProfile(
                    newMember,
                    blogUrl,
                    district,
                    birthDate,
                    introduction
            );

            // then
            assertAll(
                    () -> assertEquals(blogUrl, newMember.getBlogUrlAsValue()),
                    () -> assertEquals(district, newMember.getDistrict()),
                    () -> assertEquals(birthDate, newMember.getBirthDateAsValue()),
                    () -> assertEquals(introduction, newMember.getIntroductionAsValue())
            );
        }
    }
}
