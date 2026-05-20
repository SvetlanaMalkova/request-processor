package com.mpv.requestprocessor.service;

import com.mpv.requestprocessor.dto.NotificationRequest;
import com.mpv.requestprocessor.enums.NotificationType;
import com.mpv.requestprocessor.strategy.NotificationStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class NotificationService {

    private final Map<NotificationType, NotificationStrategy> strategies;

    public NotificationService(List<NotificationStrategy> strategyList) {
        this.strategies = strategyList.stream()
                .collect(Collectors.toMap(
                        NotificationStrategy::getType,
                        Function.identity()
                ));
    }
    public void process (NotificationRequest request) {
        NotificationStrategy strategy = strategies.get(request.getType());

        if (strategy == null) {
            throw new IllegalArgumentException("Неизвестный тип уведомления: " + request.getType());
        }

        strategy.process (request.getMessage());
    }
}
