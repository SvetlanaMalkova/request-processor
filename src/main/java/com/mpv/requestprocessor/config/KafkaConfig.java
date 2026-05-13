package com.mpv.requestprocessor.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaConfig {

    @Bean
    public NewTopic smsEvents() {
        return TopicBuilder.name("sms-events").partitions(1).replicas((short) 1).build();
    }

    @Bean
    public NewTopic emailEvents() {
        return TopicBuilder.name("email-events").partitions(1).replicas((short) 1).build();
    }

    @Bean
    public NewTopic pushEvents() {
        return TopicBuilder.name("push-events").partitions(1).replicas((short) 1).build();
    }

    @Bean
    public NewTopic telegramEvents() {
        return TopicBuilder.name("telegram-events").partitions(1).replicas((short) 1).build();
    }
}
