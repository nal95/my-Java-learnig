package com.nal95.clinic.dto.responses;

import com.nal95.clinic.Title;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

public class UserRest {
        private String firstName;
    private String lastName;
    private String email;

    @Enumerated(EnumType.STRING)
    private Title title;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Title getTitle() {
        return title;
    }

    public void setTitle(Title title) {
        this.title = title;
    }
}
