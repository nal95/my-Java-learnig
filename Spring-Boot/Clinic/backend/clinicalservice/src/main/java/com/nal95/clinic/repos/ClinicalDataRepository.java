package com.nal95.clinic.repos;

import com.nal95.clinic.model.ClinicalData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ClinicalDataRepository extends JpaRepository<ClinicalData, Integer> {
    List<ClinicalData> findByPatientIdAndComponentNameOrderByMeasuredDateTime(int patientId, String componentName);
}
