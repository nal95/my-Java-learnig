package com.nal95.clinic_v1.services;

import com.nal95.clinic_v1.dto.request.PatientRequest;

public interface PatientService {
    boolean addPatient(PatientRequest newPatient);
    void verifyAccount(String token);
}
