package com.nal95.clinic.api;

import com.nal95.clinic.dto.ClinicalDataRequest;
import com.nal95.clinic.model.ClinicalData;
import com.nal95.clinic.model.Patient;
import com.nal95.clinic.repos.ClinicalDataRepository;
import com.nal95.clinic.repos.PatientRepository;
import com.nal95.clinic.util.BMICalculator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class ClinicalDataController {

    private final ClinicalDataRepository clinicalDataRepository;
    private final PatientRepository patientRepository;

    private static final Logger LOGGER = LoggerFactory.getLogger(ClinicalDataController.class);
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

    @GetMapping(value="/clinicals/{patientId}/{componentName}")
    public List<ClinicalData> getClinicalData(@PathVariable("patientId") int patientId,
                                              @PathVariable("componentName") String componentName){
        if(componentName.equalsIgnoreCase("BMI")){
            componentName = "hw";
        }
        List<ClinicalData> clinicalData = clinicalDataRepository.findByPatientIdAndComponentNameOrderByMeasuredDateTime(patientId,componentName);
        LOGGER.info("clinicalData: " + clinicalData  );
        List<ClinicalData> duplicateClinicalData = new ArrayList<>(clinicalData);
        for(ClinicalData eachEntry:duplicateClinicalData){
            BMICalculator.calculateBMI(clinicalData,eachEntry);
        }
        return clinicalData;
    }
}
