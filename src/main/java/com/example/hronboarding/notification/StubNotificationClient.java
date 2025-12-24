package com.example.hronboarding.notification;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class StubNotificationClient implements NotificationClient {

    @Override
    public void notify(String recipient, String subject, String body) {
        log.info("Notify -> to: {}, subject: {}, body: {}", recipient, subject, body);
    }
}
