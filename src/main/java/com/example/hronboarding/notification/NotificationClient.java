package com.example.hronboarding.notification;

public interface NotificationClient {
    void notify(String recipient, String subject, String body);
}
