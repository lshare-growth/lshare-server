package com.example.backend.business.core.notice.entity;

import com.example.backend.business.core.admin.entity.Admin;
import com.example.backend.business.core.common.Deleted;
import com.example.backend.business.core.common.values.DateTime;
import com.example.backend.business.core.common.values.ViewCount;
import com.example.backend.business.core.image.entity.vo.ThumbNailImageUrl;
import com.example.backend.business.core.notice.entity.values.NoticeContent;
import com.example.backend.business.core.notice.entity.values.NoticeImages;
import com.example.backend.business.core.notice.entity.values.NoticeTitle;
import com.example.backend.common.exception.BusinessException;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;
import java.util.Objects;

import static com.example.backend.common.exception.member.MemberTypeException.UNAUTHORIZED_EXCEPTION;

@Entity
public class Notice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long noticeId;

    @Embedded
    private NoticeTitle noticeTitle;

    @Embedded
    private NoticeContent noticeContent;

    @Embedded
    private NoticeImages noticeImages;

    @Embedded
    private ThumbNailImageUrl thumbNailImageUrl;

    @Embedded
    private ViewCount viewCount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "admin_id")
    private Admin admin;

    @Column(columnDefinition = "ENUM('ALARAM', 'NOTICE')")
    @Enumerated(EnumType.STRING)
    private NoticeType noticeType;

    @Embedded
    private DateTime dateTime;

    @Column(columnDefinition = "ENUM('TRUE', 'FALSE')")
    @Enumerated(EnumType.STRING)
    private Deleted deleted;

    /**
     * @Nullary-Constructor. JPA 기본 생성자로 notice 외부 패키지에서 호출하지 말 것.
     */
    protected Notice() {
    }

    public Notice(Admin admin,
                  NoticeTitle noticeTitle,
                  NoticeContent noticeContent) {

        this.admin = admin;
        this.noticeTitle = noticeTitle;
        this.noticeContent = noticeContent;
        this.dateTime = DateTime.initDateTime();
        this.viewCount = ViewCount.initViewCount();
        this.deleted = Deleted.FALSE;
    }

    public Long getNoticeId() {
        return noticeId;
    }

    public NoticeTitle getNoticeTitleAsValue() {
        return noticeTitle;
    }

    public String getNoticeTitle() {
        return noticeTitle.getTitle();
    }

    public NoticeContent getNoticeContentAsValue() {
        return noticeContent;
    }

    public String getNoticeContent() {
        return noticeContent.getContent();
    }

    public LocalDateTime getCreatedAt() {
        return dateTime.getCreatedAt();
    }

    public LocalDateTime getLastModifiedAt() {
        return dateTime.getLastModifiedAt();
    }

    public ViewCount getViewCount() {
        return viewCount;
    }

    public Admin getAdmin() {
        return admin;
    }

    public String getAdminNickName() {
        return admin.getNickName();
    }

    public String getAdminProfileImageUrl() {
        return admin.getProfileImageUrl();
    }

    public void delete(Admin admin) {
        validateAdminAuthorize(admin);
    }

    private void validateAdminAuthorize(Admin admin) {
        if (!this.admin.equals(admin)) {
            throw new BusinessException(UNAUTHORIZED_EXCEPTION);
        }
    }

    public void updateNotice(Admin admin, NoticeTitle noticeTitle, NoticeContent noticeContent) {
        validateAdminAuthorize(admin);
        this.noticeTitle = noticeTitle;
        this.noticeContent = noticeContent;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Notice)) return false;
        Notice notice = (Notice) o;
        return noticeId.equals(notice.noticeId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(noticeId);
    }

    @Override
    public String toString() {
        return noticeId.toString();
    }
}
