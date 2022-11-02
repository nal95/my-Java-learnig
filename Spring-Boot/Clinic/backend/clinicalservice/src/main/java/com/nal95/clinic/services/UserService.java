package com.nal95.clinic.services;

import com.nal95.clinic.dto.requests.UserRegistrationRequest;
import com.nal95.clinic.dto.responses.UserResponse;
import com.nal95.clinic.model.Role;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {
    void signup(UserRegistrationRequest user);
    boolean saveUser(UserRegistrationRequest user);
    Role saveRole(Role role);
    void addRoleToUser(String email, String roleName);
    UserResponse getUser(String email);
    List<UserResponse> getUsers();
    boolean verifyAccount(String token);
}
