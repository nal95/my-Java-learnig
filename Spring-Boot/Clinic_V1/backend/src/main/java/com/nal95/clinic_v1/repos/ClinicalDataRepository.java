package com.nal95.clinic_v1.repos;

import com.nal95.clinic_v1.model.ClinicalData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClinicalDataRepository extends JpaRepository<ClinicalData, Long> {
    List<ClinicalData> findByPatientIdAndComponentNameOrderByMeasuredDateTime(long patientId,String componentName);
}
