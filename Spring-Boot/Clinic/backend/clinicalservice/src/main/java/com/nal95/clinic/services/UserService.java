package com.nal95.clinic.services;

import com.nal95.clinic.dto.request.UserRegistrationRequest;
import com.nal95.clinic.dto.response.UserResponse;
import com.nal95.clinic.model.Role;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {

    boolean saveUser(UserRegistrationRequest user);

    Role saveRole(Role role);

    void addRoleToUser(String email, String roleName);

    UserResponse getUser(String email);

    List<UserResponse> getUsers();
}
