package com.example.backend.business.web.notice.presentation;

import com.example.backend.business.core.admin.entity.value.pojo.AdminId;
import com.example.backend.business.core.notice.entity.Notice;
import com.example.backend.business.core.notice.entity.values.NoticeContent;
import com.example.backend.business.core.notice.entity.values.NoticeTitle;
import com.example.backend.business.core.notice.entity.values.pojo.NoticeId;
import com.example.backend.business.web.notice.facade.NoticeCommandFacade;
import com.example.backend.business.web.notice.presentation.dto.request.NoticeUpdateRequest;
import com.example.backend.business.web.notice.presentation.dto.request.NoticeWriteRequest;
import com.example.backend.business.web.notice.presentation.dto.response.NoticeWriteResponse;
import com.example.backend.common.login.annotation.Authenticated;
import com.example.backend.common.login.model.authentication.AuthenticatedMember;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/notices")
public class NoticeCommandController {

    private final NoticeCommandFacade noticeCommandFacade;

    @PostMapping
    public ResponseEntity<NoticeWriteResponse> writeNotice(@Authenticated AuthenticatedMember authenticatedMember,
                                                           @RequestBody NoticeWriteRequest request) {

        Notice newNotice = noticeCommandFacade.writeNotice(
                AdminId.from(authenticatedMember.getAuthenticatedId()),
                NoticeTitle.from(request.getNoticeTitle()),
                NoticeContent.from(request.getNoticeContent())
        );

        return new ResponseEntity<>(NoticeWriteResponse.of(newNotice), HttpStatus.CREATED);
    }

    @PutMapping("/{noticeId}")
    public ResponseEntity<Void> updateNotice(@Authenticated AuthenticatedMember authenticatedMember,
                                             @PathVariable Long noticeId,
                                             @RequestBody NoticeUpdateRequest request) {

        noticeCommandFacade.updateNotice(
                AdminId.from(authenticatedMember.getAuthenticatedId()),
                NoticeId.from(noticeId),
                NoticeTitle.from(request.getNoticeTitle()),
                NoticeContent.from(request.getNoticeContent())
        );

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{noticeId}")
    public ResponseEntity<Void> deleteNotice(@Authenticated AuthenticatedMember authenticatedMember,
                                             @PathVariable Long noticeId) {

        noticeCommandFacade.deleteNotice(
                AdminId.from(authenticatedMember.getAuthenticatedId()),
                NoticeId.from(noticeId)
        );

        return ResponseEntity.ok().build();
    }
}
