package com.nal95.clinic.services;

import com.nal95.clinic.dto.UserDto;
import com.nal95.clinic.model.User;

public interface UserService {
    UserDto createUser(User user);
}
