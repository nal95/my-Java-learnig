package com.nal95.clinic.services;

import com.nal95.clinic.dto.requests.NotificationEmail;
import com.nal95.clinic.exceptions.AuthException;
import com.nal95.clinic.utils.AppRouteConstants;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class MailServiceImpl implements MailService{

    private final JavaMailSender mailSender;
    private final MailContentBuilder mailContentBuilder;

    @Override
    @Async
    public void sendMail(NotificationEmail notificationEmail, String token) {

        MimeMessagePreparator messagePreparator = mimeMessage -> {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
            messageHelper.setFrom(AppRouteConstants.EMAIL);
            messageHelper.setTo(notificationEmail.getRecipient());
            messageHelper.setSubject(notificationEmail.getSubject());
            String link = AppRouteConstants.VERIFICATION_LINK + token;
            String content = mailContentBuilder.build(notificationEmail.getBody(),link);
            messageHelper.setText(content,true);
        };
        try {
            mailSender.send(messagePreparator);
            log.info("Activation email sent!!");
        } catch (MailException e) {
            log.error("Exception occurred when sending mail", e);
            throw new AuthException("Exception occurred when sending mail to " + notificationEmail.getRecipient(), e);
        }
    }
}
