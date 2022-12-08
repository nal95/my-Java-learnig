package com.nal95.clinic_v1.services;

import com.nal95.clinic_v1.dto.request.PatientRequest;
import com.nal95.clinic_v1.model.Patient;
import com.nal95.clinic_v1.repos.PatientRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;

@Service
@RequiredArgsConstructor
@Slf4j
public class PatientServiceImpl implements PatientService {

    private final PatientRepository patientRepository;

    @Override
    public boolean addPatient(PatientRequest newPatient) {
        if((patientRepository.findUserByEmail(newPatient.getEmail())) != null)
            throw new RuntimeException("DB already have this Email");

        Patient patient = new Patient();
        BeanUtils.copyProperties(patient,newPatient);
        patient.setCreated(Timestamp.from(Instant.now()));
        patient.setEnabled(false);
        return true;
    }
}
