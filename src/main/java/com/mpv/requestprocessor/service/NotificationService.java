package com.mpv.requestprocessor.service;

import com.mpv.requestprocessor.dto.NotificationRequest;
import com.mpv.requestprocessor.enums.NotificationType;
import com.mpv.requestprocessor.strategy.NotificationStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class NotificationService {

    private final Map<String, NotificationStrategy> strategies;

    public void process (NotificationRequest request) {
        String strateqyName = resolveStrategyName(request.getType());
        NotificationStrategy strategy = strategies.get(strateqyName);

        if (strategy == null) {
            throw new IllegalArgumentException("Неизвестный тип уведомления: " + request.getType());
        }

        strategy.process (request.getMessage());
    }

    private String resolveStrategyName(NotificationType type){
        return switch (type){
            case SMS -> "smsNotificationStrategy";
            case EMAIL -> "emailNotificationStrategy";
            case PUSH -> "pushNotificationStrategy";
            case TG_MESSAGE -> "telegramNotificationStrategy";
        };
    }
}
