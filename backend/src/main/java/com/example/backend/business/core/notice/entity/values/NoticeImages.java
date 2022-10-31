package com.example.backend.business.core.notice.entity.values;

import com.example.backend.business.core.notice.entity.NotificationImage;

import javax.persistence.Embeddable;
import javax.persistence.OneToMany;
import java.util.List;

@Embeddable
public class NoticeImages {

    @OneToMany(mappedBy = "notice")
    private List<NotificationImage> noticeImages;

    /**
     * @Nullary-Constructor. JPA 기본 생성자로 notice 외부 패키지에서 호출하지 말 것.
     */
    protected NoticeImages() {
    }

    public NoticeImages(List<NotificationImage> noticeImages) {
        this.noticeImages = noticeImages;
    }

    public List<NotificationImage> getNoticeImages() {
        return noticeImages;
    }

    @Override
    public String toString() {
        return String.format("공지사항 이미지 목록: %s", noticeImages);
    }
}
