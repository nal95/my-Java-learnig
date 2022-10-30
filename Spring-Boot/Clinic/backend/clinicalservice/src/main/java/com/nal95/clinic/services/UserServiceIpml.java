package com.nal95.clinic.services;

import com.nal95.clinic.dto.request.UserRegistrationRequest;
import com.nal95.clinic.dto.response.UserResponse;
import com.nal95.clinic.model.Role;
import com.nal95.clinic.model.User;
import com.nal95.clinic.repos.RoleRepository;
import com.nal95.clinic.repos.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
@AllArgsConstructor
public class UserServiceIpml implements UserService {

    private final UserRepository userRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final RoleRepository roleRepository;

    @Override
    @Transactional
    public boolean saveUser(UserRegistrationRequest user) {
        log.info("Saving a new user");
        if (userRepository.findUserByEmail(user.getEmail()) != null || userRepository.findUserByUsername(user.getUsername())!= null )
            throw new RuntimeException("DB already have this Credentials");

        User userToSave = new User();
        BeanUtils.copyProperties(user, userToSave);

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
    @Transactional
    public Role saveRole(Role role) {
        log.info("Adding new Role in db " + role.getName());
        return roleRepository.save(role);
    }

    @Override
    public void addRoleToUser(String email, String roleName) {
        String name = userRepository.findUserByEmail(email).getFirstName();
        log.info("Adding new Role {} to User {} " + roleName + name);

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
}
