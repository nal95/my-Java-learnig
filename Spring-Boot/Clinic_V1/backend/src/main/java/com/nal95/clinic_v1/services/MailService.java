package com.nal95.clinic_v1.services;

import com.nal95.clinic_v1.dto.request.NotificationEmail;

public interface MailService {
    void sendMail(NotificationEmail notificationEmail, String token);

}
