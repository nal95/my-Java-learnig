package com.nal95.clinic.services;

import com.nal95.clinic.dto.requests.NotificationEmail;
import com.nal95.clinic.dto.requests.UserRegistrationRequest;
import com.nal95.clinic.dto.responses.UserResponse;
import com.nal95.clinic.exceptions.AuthException;
import com.nal95.clinic.model.Role;
import com.nal95.clinic.model.User;
import com.nal95.clinic.model.VerificationToken;
import com.nal95.clinic.repos.RoleRepository;
import com.nal95.clinic.repos.UserRepository;
import com.nal95.clinic.repos.VerificationTokenRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.*;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserServiceImpl implements UserService {
    private final VerificationTokenRepository verificationTokenRepository;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final MailService mailService;

    @Override
    public void signup(UserRegistrationRequest request) {

        User user = userRepository.findUserByUsername(request.getUsername());
        String token = generateVerificationToken(user);
        String subject = "Account Activation";
        String recipient = user.getEmail();
        String body = "Welcome " + user.getTitle() + ". " + user.getLastName().toUpperCase()+ " "
                + user.getFirstName().toUpperCase()
                + "\nTo activate your account please ";
        mailService.sendMail(new NotificationEmail(subject,recipient,body),token);
    }

    @Override
    @Transactional
    public boolean saveUser(UserRegistrationRequest user) {

        log.info("Saving a new user");
        if (userRepository.findUserByEmail(user.getEmail()) != null ||  userRepository.findUserByUsername(user.getUsername())!= null)
            throw new RuntimeException("DB already have this Email");

        User userToSave = new User();
        BeanUtils.copyProperties(user,userToSave);

        String publicUserId = UUID.randomUUID().toString();
        userToSave.setUserId(publicUserId);
        userToSave.setPassword(bCryptPasswordEncoder.encode(userToSave.getPassword()));
        userToSave.setCreated(Instant.now());
        userToSave.setEnabled(false);

        log.info("Saving a new user {}", userToSave);
        userToSave = userRepository.save(userToSave);

        return !userToSave.equals(new User());
    }

    @Override
    public Role saveRole(Role role) {
        log.info("Adding new Role in db {} ", role.getName());
        return roleRepository.save(role);
    }

    @Override
    @Transactional
    public void addRoleToUser(String email, String roleName) {
        String name = userRepository.findUserByEmail(email).getFirstName();
        log.info("Adding new Role {} to User {} ", roleName, name);
        User user = userRepository.findUserByEmail(email);
        Role role = roleRepository.findByName(roleName);
        user.getRoles().add(role);
    }

    @Override
    public UserResponse getUser(String email) {
        log.info("Fetching user by email {}", email);
        User user = userRepository.findUserByEmail(email);
        if (user == null) throw new UsernameNotFoundException(email);
        UserResponse response = new UserResponse();
        BeanUtils.copyProperties(user, response);
        return response;
    }

    @Override
    public List<UserResponse> getUsers() {
        log.info("Fetching all users");
        List<User> users = userRepository.findAll();

        List<UserResponse> responses = new ArrayList<>();
        users.forEach(user -> {
            UserResponse response = new UserResponse();
            BeanUtils.copyProperties(user, response);
            responses.add(response);
        });
        return responses;
    }

    @Override
    @Transactional
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

    private String generateVerificationToken(User user){
        String token = UUID.randomUUID().toString();
        VerificationToken verificationToken = new VerificationToken();
        verificationToken.setToken(token);
        verificationToken.setUser(user);

        verificationTokenRepository.save(verificationToken);
        return token;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findUserByEmail(email);
        if (user == null){
            log.error("User not found in the DB");
            throw new UsernameNotFoundException("User not found in the DB");
        }else {
            log.info("User found in the DB" + user.getFirstName());
        }
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        user.getRoles().forEach(role -> authorities.add(new SimpleGrantedAuthority(role.getName())));

        return new org.springframework.security
                .core.userdetails.User(user.getEmail(), user.getPassword(), authorities);
    }
}
