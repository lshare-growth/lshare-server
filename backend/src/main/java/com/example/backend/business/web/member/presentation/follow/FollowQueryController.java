package com.example.backend.business.web.member.presentation.follow;

import com.example.backend.business.core.common.values.Cursor;
import com.example.backend.business.core.member.entity.Member;
import com.example.backend.business.core.member.entity.values.Followers;
import com.example.backend.business.core.member.entity.values.MemberId;
import com.example.backend.business.web.member.facade.member.MemberQueryFacade;
import com.example.backend.business.web.member.presentation.member.dto.response.FollowHistoryExistResponse;
import com.example.backend.business.web.member.presentation.member.dto.response.FollowerListResponse;
import com.example.backend.business.web.member.presentation.member.dto.response.MemberInformationHoverResponse;
import com.example.backend.common.configuration.common.page.CursorPageable;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Objects;

import static com.example.backend.business.core.member.entity.FollowHistory.NON_EXIST;
import static com.example.backend.common.api.ApiUtils.getMemberId;
import static com.example.backend.common.api.ApiUtils.getMemberIdAsValue;

@RestController
@RequestMapping("/api/members")
@RequiredArgsConstructor
public class FollowQueryController {

    private final MemberQueryFacade memberQueryFacade;

    @GetMapping("/{memberId}/hover-info")
    public ResponseEntity<MemberInformationHoverResponse> findMemberById(@PathVariable Long memberId) {

        Member findMember = memberQueryFacade.findMemberById(
                MemberId.from(memberId)
        );

        return ResponseEntity.ok(MemberInformationHoverResponse.of(findMember));
    }

    @GetMapping("/{memberId}/friendship/follow-history")
    public ResponseEntity<FollowHistoryExistResponse> findFollowHistoryById(@PathVariable Long memberId,
                                                                            HttpServletRequest httpServletRequest) {

        if (isGuest(httpServletRequest)) {
            return ResponseEntity.ok(FollowHistoryExistResponse.of(NON_EXIST));
        }

        FollowHistoryExistResponse response = memberQueryFacade.findFollowHistoryById(
                getMemberIdAsValue(httpServletRequest),
                MemberId.from(memberId)
        );

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{memberId}/friendship/following-list")
    public ResponseEntity<FollowerListResponse> findFollowingList(@PathVariable Long memberId,
                                                                  @CursorPageable Cursor cursor,
                                                                  HttpServletRequest httpServletRequest) {

        Followers followingList = memberQueryFacade.findFollowingListById(
                MemberId.from(memberId),
                cursor
        );

        boolean hasNext = followingList.hasNext();

        if (isGuest(httpServletRequest)) {
            return ResponseEntity.ok(FollowerListResponse.of(followingList, hasNext));
        }

        List<Long> myFollowerList = getMyFollwerList(
                getMemberIdAsValue(httpServletRequest),
                followingList.getSourceIds()
        );

        return ResponseEntity.ok(FollowerListResponse.of(followingList, myFollowerList, hasNext));
    }

    @GetMapping("/{memberId}/friendship/follower-list")
    public ResponseEntity<FollowerListResponse> findFollowerList(@PathVariable Long memberId,
                                                                 @CursorPageable Cursor cursor,
                                                                 HttpServletRequest httpServletRequest) {

        Followers followerList = memberQueryFacade.findFollowerListById(
                MemberId.from(memberId),
                cursor
        );

        boolean hasNext = followerList.hasNext();

        if (isGuest(httpServletRequest)) {
            return ResponseEntity.ok(FollowerListResponse.of(followerList, hasNext));
        }

        List<Long> myFollowerList = getMyFollwerList(
                getMemberIdAsValue(httpServletRequest),
                followerList.getTargetIds()
        );

        return ResponseEntity.ok(FollowerListResponse.of(followerList, myFollowerList, hasNext));
    }

    private List<Long> getMyFollwerList(MemberId memberId, List<Long> getTargetIds) {
        return memberQueryFacade.findMyFollowList(
                memberId,
                getTargetIds
        );
    }

    private static boolean isGuest(HttpServletRequest httpServletRequest) {
        return Objects.isNull(getMemberId(httpServletRequest));
    }
}
