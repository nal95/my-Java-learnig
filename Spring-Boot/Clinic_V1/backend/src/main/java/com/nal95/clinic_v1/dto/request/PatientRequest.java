package com.nal95.clinic_v1.dto.request;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class PatientRequest {
    private String firstName;
    private String lastName;
    private int age;
    private String email;
}
