package com.mpv.requestprocessor.strategy;

import com.mpv.requestprocessor.enums.NotificationType;

public interface NotificationStrategy {
    void process(String message);

    NotificationType getType();
}
