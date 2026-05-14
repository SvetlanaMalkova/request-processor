package com.mpv.requestprocessor.dto;

import com.mpv.requestprocessor.enums.NotificationType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NotificationRequest {

    @NotNull
    private NotificationType type;

    @NotBlank
    private String message;
}
