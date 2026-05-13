package com.mpv.requestprocessor.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "outbox")
public class OutboxProperties {

    private int batchSize;
    private long delayMs;
}
