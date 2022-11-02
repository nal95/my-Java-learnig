package com.nal95.clinic.services;

import com.nal95.clinic.dto.requests.NotificationEmail;

public interface MailService {
    void sendMail(NotificationEmail notificationEmail, String token);
}
