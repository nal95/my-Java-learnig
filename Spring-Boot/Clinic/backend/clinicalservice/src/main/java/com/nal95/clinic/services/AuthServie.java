package com.nal95.clinic.services;

import com.nal95.clinic.dto.request.UserRegistrationRequest;
import com.nal95.clinic.model.User;

public interface AuthServie {
    void signup(UserRegistrationRequest user);
    User getCurrentUser();
}
