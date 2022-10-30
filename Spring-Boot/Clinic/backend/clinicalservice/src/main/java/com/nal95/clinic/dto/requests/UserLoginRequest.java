package com.nal95.clinic.dto.requests;

import lombok.Data;

@Data
public class UserLoginRequest {
    private String username;
    private String password;

}
