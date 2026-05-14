package com.mpv.requestprocessor.schelduler;

import com.mpv.requestprocessor.config.OutboxProperties;
import com.mpv.requestprocessor.entity.NotificationOutbox;
import com.mpv.requestprocessor.repository.NotificationOutboxRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class OutboxScheduler {

    private final NotificationOutboxRepository outboxRepository;
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final OutboxProperties outboxProperties;

    @Scheduled(fixedDelayString = "${outbox.delay-ms}")
    public void processOutbox() {
        List<NotificationOutbox> messages = outboxRepository.findBySentFalseOrderByCreatedAtAsc(
                PageRequest.of(0, outboxProperties.getBatchSize())
        );

        for (NotificationOutbox message : messages) {
            try {
                kafkaTemplate.send(message.getTopic(), message.getKey(), message.getValue()).get();
                message.setSent(true);
                outboxRepository.save(message);
            } catch (Exception e) {
                log.error("Ошибка отправки сообщения key={}: {}", message.getKey(), e.getMessage());
                message.setAttempt(message.getAttempt() + 1);
                outboxRepository.save(message);
            }
        }
    }
}
