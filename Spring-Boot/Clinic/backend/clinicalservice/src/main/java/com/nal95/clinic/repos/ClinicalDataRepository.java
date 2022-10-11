package com.nal95.clinic.repos;

import com.nal95.clinic.model.ClinicalData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClinicalDataRepository extends JpaRepository<ClinicalData, Integer> {
}
