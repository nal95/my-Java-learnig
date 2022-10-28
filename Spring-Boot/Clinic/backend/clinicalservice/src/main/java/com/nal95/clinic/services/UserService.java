package com.nal95.clinic.services;

import com.nal95.clinic.dto.requests.UserDtoRequest;
import com.nal95.clinic.model.Role;
import com.nal95.clinic.model.User;

import java.util.List;

public interface UserService {
    User saveUser(UserDtoRequest user);

    Role saveRole(Role role);

    void addRoleToUser(String email, String roleName);

    User getUser(String email);

    List<User> getUsers();
}
