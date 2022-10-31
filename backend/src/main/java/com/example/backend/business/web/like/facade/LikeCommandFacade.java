package com.example.backend.business.web.like.facade;

import com.example.backend.business.core.like.entity.LikeClicked;
import com.example.backend.business.core.member.entity.Member;
import com.example.backend.business.core.member.entity.values.MemberId;
import com.example.backend.business.core.study.entity.Study;
import com.example.backend.business.core.study.entity.values.StudyId;
import com.example.backend.business.web.like.application.LikeCommandService;
import com.example.backend.business.web.like.application.LikeQueryService;
import com.example.backend.business.web.member.application.member.MemberQueryService;
import com.example.backend.business.web.study.application.StudyQueryServices;
import com.example.backend.common.exception.BusinessException;
import com.example.backend.common.exception.study.StudyTypeException;
import lombok.RequiredArgsConstructor;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.TimeUnit;

import static com.example.backend.common.exception.member.MemberTypeException.MEMBER_NOT_FOUND_EXCEPTION;

@Component
@RequiredArgsConstructor
public class LikeCommandFacade {

    private final RedissonClient redissonClient;
    private final StudyQueryServices studyQueryServices;
    private final MemberQueryService memberQueryService;
    private final LikeQueryService likeQueryService;
    private final LikeCommandService likeCommandService;

    /**
     * 개설된 스터디에 좋아요를 추가/삭제하고 좋아요수를 증가 또는 감소시킨다.
     * Params: memberId - 회원 아이디
     *         studyId  - 스터디 아이디
     * Throws: StudyNotFoundException  - 스터디를 찾을 수 없는 경우
     *         MemberNotFoundException - 회원을 찾을 수 없는 경우
     * Note: 스터디에서 좋아요수를 관리하기 때문에 저장된 좋아요수와 실제 좋아요 개수가 일치하지 않을 수 있다.
     *       이런 동시성 이슈 제어를 위해 RedissonClient가 제공하는 락을 사용한다.
     * Since: 2022-10-13
     * */
    @Transactional
    public void updateLike(MemberId memberId, StudyId studyId) {
        RLock lock = redissonClient.getLock(studyId.toString());
        try {
            boolean available = lock.tryLock(2, 1, TimeUnit.SECONDS);

            if (!available) {
                Thread.sleep(500);
            }

            Study findStudy = studyQueryServices.findStudyById(studyId).orElseThrow(() -> new BusinessException(StudyTypeException.STUDY_NOT_FOUND_EXCEPTION));
            Member findMember= memberQueryService.findById(memberId).orElseThrow(()->new BusinessException(MEMBER_NOT_FOUND_EXCEPTION));
            LikeClicked likeClicked = likeQueryService.exist(memberId, studyId);

            likeCommandService.updateLike(likeClicked, memberId, findStudy);
        } catch (InterruptedException e) {
            throw new RuntimeException();
        } finally {
            lock.unlock();
        }
    }
}
