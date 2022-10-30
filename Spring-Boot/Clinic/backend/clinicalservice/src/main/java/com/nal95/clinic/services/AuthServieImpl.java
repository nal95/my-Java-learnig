package com.nal95.clinic.services;

import com.nal95.clinic.dto.request.NotificationEmail;
import com.nal95.clinic.dto.request.UserRegistrationRequest;
import com.nal95.clinic.model.User;
import com.nal95.clinic.model.VerificationToken;
import com.nal95.clinic.repos.UserRepository;
import com.nal95.clinic.repos.VerificationTokenRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@AllArgsConstructor
@Service
public class AuthServieImpl implements AuthServie{

    private VerificationTokenRepository verificationTokenRepository;
    private UserRepository userRepository;

    private MailService mailService;

    @Override
    public void signup(UserRegistrationRequest request) {

        User user = userRepository.findUserByUsername(request.getUsername());
        String token = generateVerifacationToken(user);
        String subject = "Account Activation";
        String recipient = user.getEmail();
        String body = "Welcome " + user.getTitle() + ". " + user.getLastName().toUpperCase()+ ". "
                + user.getFirstName().toUpperCase()
                + "\nTo activate your account please ";
        mailService.sendMail(new NotificationEmail(subject,recipient,body),token);
    }

    @Override
    public User getCurrentUser() {
        return null;
    }

    private String generateVerifacationToken(User user){
        String token = UUID.randomUUID().toString();
        VerificationToken verificationToken = new VerificationToken();
        verificationToken.setToken(token);
        verificationToken.setUser(user);

        verificationTokenRepository.save(verificationToken);
        return token;
    }
}
