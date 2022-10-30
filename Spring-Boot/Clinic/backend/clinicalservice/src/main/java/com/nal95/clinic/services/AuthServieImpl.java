package com.nal95.clinic.services;

import com.nal95.clinic.dto.request.NotificationEmail;
import com.nal95.clinic.dto.request.UserRegistrationRequest;
import com.nal95.clinic.exceptions.AuthException;
import com.nal95.clinic.model.User;
import com.nal95.clinic.model.VerificationToken;
import com.nal95.clinic.repos.UserRepository;
import com.nal95.clinic.repos.VerificationTokenRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
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
        String body = "Welcome " + user.getTitle() + ". " + user.getLastName().toUpperCase()+ " "
                + user.getFirstName().toUpperCase()
                + "\nTo activate your account please ";
        mailService.sendMail(new NotificationEmail(subject,recipient,body),token);
    }

    @Override
    public User getCurrentUser() {
        return null;
    }

    @Override
    public boolean verifyAccount(String token) {
        Optional<VerificationToken> verificationToken = verificationTokenRepository.findByToken(token);
        if (verificationToken.isEmpty()) {
            throw new AuthException("Invalide Token");
        }
        VerificationToken value = verificationToken.get();

        return fetchUserAndEnable(value);
    }

    private boolean fetchUserAndEnable(VerificationToken verificationToken) {
        String username = verificationToken.getUser().getUsername();
        User user = userRepository.findUserByUsername(username);
        if(!user.equals(new User())){
            user.setEnabled(true);
            user = userRepository.save(user);
        }else {
            throw new AuthException("User not found with name - " + username);
        }

        return !user.equals(new User());
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
