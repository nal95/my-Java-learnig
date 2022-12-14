package com.nal95.clinic.services;

import com.nal95.clinic.dto.request.NotificationEmail;

public interface MailService {
    void sendMail(NotificationEmail notificationEmail, String token);

}
