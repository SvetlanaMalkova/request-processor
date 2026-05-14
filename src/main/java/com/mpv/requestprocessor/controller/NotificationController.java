package com.mpv.requestprocessor.controller;

import com.mpv.requestprocessor.dto.NotificationRequest;
import com.mpv.requestprocessor.service.NotificationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/notifications")
@RequiredArgsConstructor
public class NotificationController {

    private final NotificationService notificationService;

    @PostMapping
    public ResponseEntity<Void> send (@Valid @RequestBody NotificationRequest request) {
        notificationService.process(request);
        return ResponseEntity.ok().build();
    }
}
