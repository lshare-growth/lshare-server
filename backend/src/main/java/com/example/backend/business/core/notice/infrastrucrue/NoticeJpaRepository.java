package com.example.backend.business.core.notice.infrastrucrue;

import com.example.backend.business.core.notice.entity.Notice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoticeJpaRepository extends JpaRepository<Notice, Long> {
}
