package com.mpv.requestprocessor.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "notification_outbox")
@Getter
@Setter
@NoArgsConstructor
public class NotificationOutbox {

    @Id
    private UUID id;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column (nullable = false)
    private String topic;

    @Column (nullable = false)
    private String key;

    @Column (nullable = false)
    private String value;

    @Column (nullable = false)
    private boolean sent;

    @Column (nullable = false)
    private int attempt;

}