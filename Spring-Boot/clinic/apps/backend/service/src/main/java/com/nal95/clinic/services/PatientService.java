package com.nal95.clinic.services;

import com.nal95.clinic.dto.request.PatientRequest;
import com.nal95.clinic.dto.request.PatientResponse;

public interface PatientService {
    PatientResponse addPatient(PatientRequest newPatient);

    void verifyAccount(String token);
}
