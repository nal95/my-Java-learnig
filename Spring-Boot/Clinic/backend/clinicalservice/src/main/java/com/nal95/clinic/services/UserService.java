package com.nal95.clinic.services;

import com.nal95.clinic.dto.UserDto;
import com.nal95.clinic.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    UserDto createUser(User user);
}
