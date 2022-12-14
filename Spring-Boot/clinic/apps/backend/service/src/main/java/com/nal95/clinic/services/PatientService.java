package com.nal95.clinic.services;

import com.nal95.clinic.dto.request.PatientRequest;

public interface PatientService {
    boolean addPatient(PatientRequest newPatient);
    void verifyAccount(String token);
}
