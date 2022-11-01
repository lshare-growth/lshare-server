package com.example.backend.business.web.member.presentation.follow;

import com.example.backend.business.core.common.values.Cursor;
import com.example.backend.business.core.member.entity.values.Followers;
import com.example.backend.business.core.member.entity.values.MemberId;
import com.example.backend.business.web.member.facade.follow.FollowQueryFacade;
import com.example.backend.business.web.member.presentation.follow.dto.response.FollowHistoryExistResponse;
import com.example.backend.business.web.member.presentation.follow.dto.response.FollowerListResponse;
import com.example.backend.business.web.member.presentation.follow.dto.response.FollowingListResponse;
import com.example.backend.common.configuration.common.page.CursorPageable;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/members")
public class FollowQueryController {

    private final FollowQueryFacade followQueryFacade;

    @GetMapping("/{memberId}/friendship/follow-history")
    public ResponseEntity<FollowHistoryExistResponse> findFollowHistoryById(@PathVariable Long memberId,
                                                                            HttpServletRequest httpServletRequest) {

        if (isGuest(httpServletRequest)) {
            return ResponseEntity.ok(FollowHistoryExistResponse.of(NON_EXIST));
        }

        FollowHistoryExistResponse response = followQueryFacade.findFollowHistoryById(
                getMemberIdAsValue(httpServletRequest),
                MemberId.from(memberId)
        );

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{memberId}/friendship/following-list")
    public ResponseEntity<FollowingListResponse> findFollowingList(@PathVariable Long memberId,
                                                                   @CursorPageable Cursor cursor,
                                                                   HttpServletRequest httpServletRequest) {

        Followers followingList = followQueryFacade.findFollowingListById(
                MemberId.from(memberId),
                cursor
        );

        if (isGuest(httpServletRequest)) {
            return ResponseEntity.ok(FollowingListResponse.of(followingList));
        }

        List<Long> myFollowerList = getMyFollwerList(
                getMemberIdAsValue(httpServletRequest),
                followingList.getTargetIds()
        );

        return ResponseEntity.ok(FollowingListResponse.of(followingList, myFollowerList));
    }

    @GetMapping("/{memberId}/friendship/follower-list")
    public ResponseEntity<FollowerListResponse> findFollowerList(@PathVariable Long memberId,
                                                                 @CursorPageable Cursor cursor,
                                                                 HttpServletRequest httpServletRequest) {

        Followers followerList = followQueryFacade.findFollowerListById(
                MemberId.from(memberId),
                cursor
        );

        if (isGuest(httpServletRequest)) {
            return ResponseEntity.ok(FollowerListResponse.of(followerList));
        }

        List<Long> myFollowerList = getMyFollwerList(
                getMemberIdAsValue(httpServletRequest),
                followerList.getSourceIds()
        );

        return ResponseEntity.ok(FollowerListResponse.of(followerList, myFollowerList));
    }

    private List<Long> getMyFollwerList(MemberId memberId, List<Long> getTargetIds) {
        return followQueryFacade.findMyFollowList(
                memberId,
                getTargetIds
        );
    }

    private static boolean isGuest(HttpServletRequest httpServletRequest) {
        return Objects.isNull(getMemberId(httpServletRequest));
    }
}
