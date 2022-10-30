package com.nal95.clinic.dto.response;

import com.nal95.clinic.util.Title;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.Instant;

@Getter
@Setter
public class UserResponse {

    private String userId;

    private String username;

    private String email;

    @Enumerated(EnumType.STRING)
    private Title title;

    private Instant created;

    private boolean enabled;
}
