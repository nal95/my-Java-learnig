package com.nal95.clinic.repos;

import com.nal95.clinic.model.ClinicalData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClinicalDataRepository extends JpaRepository<ClinicalData, Integer> {
    List<ClinicalData> findByPatientIdAndComponentNameOrderByMeasuredDateTime(int patientId, String componentName);
}
