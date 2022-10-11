package com.nal95.clinic.api;

import com.nal95.clinic.dto.ClinicalDataRequest;
import com.nal95.clinic.model.ClinicalData;
import com.nal95.clinic.model.Patient;
import com.nal95.clinic.repos.ClinicalDataRepository;
import com.nal95.clinic.repos.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ClinicalDataController {

    private final ClinicalDataRepository clinicalDataRepository;
    private final PatientRepository patientRepository;

    @Autowired
    ClinicalDataController(ClinicalDataRepository clinicalDataRepository,PatientRepository patientRepository){
        this.clinicalDataRepository = clinicalDataRepository;
        this.patientRepository = patientRepository;
    }
    @PostMapping("/clinicals")
    public ClinicalData saveClinicalData(@RequestBody ClinicalDataRequest request){
        Patient patient = patientRepository.findById(request.getPatientId()).orElseThrow();
        ClinicalData clinicalData = new ClinicalData();
        clinicalData.setComponentName(request.getComponentName());
        clinicalData.setComponentValue(request.getComponentValue());
        clinicalData.setPatient(patient);
        return clinicalDataRepository.save(clinicalData);
    }
}
