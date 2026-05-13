package com.mpv.requestprocessor.repository;

import com.mpv.requestprocessor.entity.NotificationOutbox;
import org.hibernate.validator.constraints.UUID;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationOutboxRepository extends JpaRepository<NotificationOutbox, UUID> {

    List<NotificationOutbox> findBySentFalseOrderByCreatedAtAsc (Pageable pageable);
}
