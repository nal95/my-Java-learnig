package com.nal95.clinic_v1.repos;

import com.nal95.clinic_v1.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient, Long> {
}
