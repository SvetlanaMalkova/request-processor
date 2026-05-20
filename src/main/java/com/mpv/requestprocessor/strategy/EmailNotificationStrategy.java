package com.mpv.requestprocessor.strategy;

import com.mpv.requestprocessor.entity.NotificationOutbox;
import com.mpv.requestprocessor.enums.NotificationType;
import com.mpv.requestprocessor.repository.NotificationOutboxRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

@Slf4j
@Component
@RequiredArgsConstructor
public class EmailNotificationStrategy implements NotificationStrategy {

    private static final String TOPIC = "email-events";
    private final NotificationOutboxRepository outboxRepository;

    @Override
    public NotificationType getType() {
        return NotificationType.EMAIL;
    }

    @Override
    @Transactional
    public void process(String message) {
        String key = UUID.randomUUID().toString();
        String body = """
                {"message": "%s"}
                """.formatted(message);

        NotificationOutbox outbox = new NotificationOutbox();
        outbox.setId(UUID.randomUUID());
        outbox.setCreatedAt(LocalDateTime.now());
        outbox.setTopic(TOPIC);
        outbox.setKey(key);
        outbox.setValue(body);
        outbox.setSent(false);
        outbox.setAttempt(1);

        outboxRepository.save(outbox);
        log.info("Подготовлено сообщение для отправки. Key: {}, Payload: {}, topic: {}", key, body, TOPIC);
    }
}
