package com.example.backend.lshare;

import com.example.backend.business.core.comment.entity.Comment;
import com.example.backend.business.core.comment.entity.values.CommentContent;
import com.example.backend.business.core.member.entity.Member;
import com.example.backend.business.core.study.entity.Study;
import com.example.backend.business.core.study.entity.values.StudyId;
import com.example.backend.business.core.tag.entity.values.TagNames;
import com.example.backend.business.web.comment.application.CommentCommandService;
import com.example.backend.business.web.comment.application.CommentQueryServices;
import com.example.backend.business.web.comment.facade.CommentCommandFacade;
import com.example.backend.business.web.member.application.member.MemberCommandService;
import com.example.backend.business.web.study.application.StudyCommandService;
import com.example.backend.business.web.study.application.StudyQueryServices;
import com.example.backend.business.web.study.facade.StudyCommandFacade;
import com.example.backend.business.web.tag.application.HashTagQueryService;
import com.example.backend.common.configuration.common.jasyp.JasyptConfiguration;
import com.example.backend.data.study.StudyTestData;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
public abstract class IntegrationTestContainer {

    @Autowired
    protected JasyptConfiguration jasyptConfiguration;

    @Autowired
    protected StudyCommandFacade studyFacade;

    @Autowired
    protected StudyQueryServices studyQueryServices;

    @Autowired
    protected StudyCommandService studyCommandService;

    @Autowired
    protected CommentCommandService commentCommandService;

    @Autowired
    protected CommentQueryServices commentQueryServices;

    @Autowired
    protected StudyCommandFacade studyCommandFacade;

    @Autowired
    protected CommentCommandFacade commentCommandFacade;

    @Autowired
    protected EntityManager entityManager;

    @Autowired
    protected MemberCommandService memberCommandService;

    @Autowired
    protected HashTagQueryService hashTagQueryService;

    /**
     * newStudy: 새로 개설된 스터디
     * alreadyEndedStudy: 이미 종료된 스터디
     *
     * <p>
     * newComment: 스터디에 작성된 댓글
     *
     * <p>
     * studyLeader: 스터디 개설자(newStudy 생성시 스터디 책임자)
     * studyRequestMember: 개설된 스터디(newStudy)에 가입 희망 멤버
     */
    protected Study newStudy;
    protected Comment newComment;
    protected Member studyLeader;
    protected Member studyRequestMember;

    /**
     * given: 스터디를 개설했을때
     */
    @BeforeEach
    void setUP() {
        // 새로운 회원 등록
        studyRequestMember = memberCommandService.save(new Member("devjun10", "www.helloworld.com"));
        studyLeader = memberCommandService.save(new Member("studyLeader", "www.helloworld.com"));

        // 새로운 스터디 개설
        newStudy = studyFacade.createStudy(
                studyLeader.getMemberIdAsValue(),
                StudyTestData.createStudyTestData(),
                TagNames.from(List.of("Spring core"))
        );

        // 스터디에 댓글 추가
        newComment = commentCommandFacade.writeComment(
                studyRequestMember.getMemberIdAsValue(),
                StudyId.from(newStudy.getStudyId()),
                CommentContent.from("Hello World")
        );
    }

    @AfterEach
    void after() {
        newStudy = null;
        newComment = null;
        studyLeader = null;
        studyRequestMember = null;

        entityManager.flush();
        entityManager.clear();
    }
}
