package com.example.backend.business.web.study.facade;

import com.example.backend.business.core.common.District;
import com.example.backend.business.core.member.entity.Member;
import com.example.backend.business.core.member.entity.values.MemberId;
import com.example.backend.business.core.study.entity.ProgressOfStudy;
import com.example.backend.business.core.study.entity.Study;
import com.example.backend.business.core.study.entity.StudyHashTag;
import com.example.backend.business.core.study.entity.StudyStatus;
import com.example.backend.business.core.study.entity.values.MaxStudyMemberCount;
import com.example.backend.business.core.study.entity.values.Milestone;
import com.example.backend.business.core.study.entity.values.StudyContent;
import com.example.backend.business.core.study.entity.values.StudyId;
import com.example.backend.business.core.study.entity.values.StudyTitle;
import com.example.backend.business.core.tag.entity.HashTag;
import com.example.backend.business.core.tag.entity.values.StudyHashTags;
import com.example.backend.business.core.tag.entity.values.TagName;
import com.example.backend.business.core.tag.entity.values.TagNames;
import com.example.backend.business.web.member.application.member.MemberQueryService;
import com.example.backend.business.web.study.application.StudyCommandService;
import com.example.backend.business.web.study.application.StudyQueryServices;
import com.example.backend.business.web.tag.application.HashTagCommandService;
import com.example.backend.business.web.tag.application.HashTagQueryService;
import com.example.backend.common.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import static com.example.backend.common.exception.member.MemberTypeException.MEMBER_NOT_FOUND_EXCEPTION;
import static com.example.backend.common.exception.study.StudyTypeException.STUDY_NOT_FOUND_EXCEPTION;
import static com.example.backend.common.exception.tag.HashTagTypeException.HASH_TAG_NOT_FOUND_EXCEPTION;

@Component
@RequiredArgsConstructor
public class StudyCommandFacade {

    private final StudyQueryServices studyQueryServices;
    private final StudyCommandService studyCommandService;
    private final MemberQueryService memberQueryService;
    private final HashTagCommandService hashTagCommandService;
    private final HashTagQueryService hashTagQueryService;
    private final RedissonClient redissonClient;

    @Transactional
    public Study createStudy(MemberId memberId,
                             Study study,
                             TagNames tagNames) {

        Member studyLeader = memberQueryService.findById(memberId).orElseThrow(() -> new BusinessException(MEMBER_NOT_FOUND_EXCEPTION));

        Study newStudy = studyCommandService.save(study);
        newStudy.registerMember(studyLeader);

        StudyHashTags newHashTags = getHashTags(tagNames, newStudy);
        newStudy.registerHashTags(newHashTags);
        return newStudy;
    }

    @Transactional
    public StudyHashTags getHashTags(TagNames tagNames, Study study) {
        Set<StudyHashTag> studyHashTags = new LinkedHashSet<>();

        for (TagName tagName : tagNames.getTagNames()) {
            if (!exist(tagName)) {
                HashTag newHashTag = hashTagCommandService.save(HashTag.createHashTag(tagName));
                studyHashTags.add(StudyHashTag.from(study, newHashTag));
                continue;
            }
            HashTag findHashTag = hashTagQueryService.findByTagName(tagName).orElseThrow(() -> new BusinessException(HASH_TAG_NOT_FOUND_EXCEPTION));
            studyHashTags.add(StudyHashTag.from(study, findHashTag));
        }
        return StudyHashTags.from(studyHashTags);
    }

    @Transactional
    public void updateStudy(MemberId memberId,
                            StudyId studyId,
                            StudyTitle title,
                            StudyContent content,
                            TagNames tagNames,
                            ProgressOfStudy progressOfStudy,
                            District district,
                            MaxStudyMemberCount maxStudyMemberCount,
                            Milestone milestone) {

        Member studyLeader = memberQueryService.findById(memberId).orElseThrow(() -> new BusinessException(MEMBER_NOT_FOUND_EXCEPTION));
        Study findStudy = studyQueryServices.findById(studyId).orElseThrow(() -> new BusinessException(STUDY_NOT_FOUND_EXCEPTION));
        StudyHashTags hashTags = findByTagName(tagNames, findStudy);

        studyCommandService.updateStudy(studyLeader, findStudy, title, content, hashTags, progressOfStudy, district, maxStudyMemberCount, milestone);
    }

    @Transactional
    public StudyHashTags findByTagName(TagNames tagNames, Study study) {
        Set<StudyHashTag> studyHashTags = new LinkedHashSet<>();

        for (TagName tagName : tagNames.getTagNames()) {
            if (!exist(tagName)) {
                HashTag newHashTag = hashTagCommandService.save(HashTag.createHashTag(tagName));
                studyHashTags.add(StudyHashTag.from(study, newHashTag));
                continue;
            }
            HashTag findHashTag = hashTagQueryService.findByTagName(tagName).orElseThrow();
            studyHashTags.add(StudyHashTag.from(study, findHashTag));
        }
        return StudyHashTags.from(studyHashTags);
    }

    @Transactional
    public void updateStudyStatus(MemberId memberId, StudyId studyId, StudyStatus studyStatus) {
        Member studyLeader = memberQueryService.findById(memberId).orElseThrow(() -> new BusinessException(MEMBER_NOT_FOUND_EXCEPTION));
        Study findStudy = studyQueryServices.findById(studyId).orElseThrow(() -> new BusinessException(STUDY_NOT_FOUND_EXCEPTION));

        studyCommandService.updateStudyStatus(studyLeader, findStudy, studyStatus);
    }

    @Transactional
    public void deleteStudy(MemberId memberId, StudyId studyId) {
        Member writer = memberQueryService.findById(memberId).orElseThrow(() -> new BusinessException(MEMBER_NOT_FOUND_EXCEPTION));
        Study findStudy = studyQueryServices.findById(studyId).orElseThrow(() -> new BusinessException(STUDY_NOT_FOUND_EXCEPTION));

        studyCommandService.deleteStudy(writer, findStudy);
    }

    @Transactional
    public boolean exist(TagName newTagName) {
        RLock lock = redissonClient.getLock(newTagName.getTagName());
        try {
            boolean available = lock.tryLock(1, 1, TimeUnit.SECONDS);

            if (!available) {
                Thread.sleep(200);
            }

            return hashTagQueryService.exist(newTagName);
        } catch (InterruptedException e) {
            throw new RuntimeException();
        } finally {
            lock.unlock();
        }
    }
}
