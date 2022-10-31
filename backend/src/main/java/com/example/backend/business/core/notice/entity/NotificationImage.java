package com.example.backend.business.core.notice.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class NotificationImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long notificationImageId;

    /**
     * @Nullary-Constructor. JPA 기본 생성자로 notice 외부 패키지에서 호출하지 말 것.
     */
    protected NotificationImage() {
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "notice_id")
    private Notice notice;

}
