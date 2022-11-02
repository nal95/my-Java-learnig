package com.nal95.clinic.api;

import com.nal95.clinic.model.ClinicalData;
import com.nal95.clinic.model.Patient;
import com.nal95.clinic.repos.PatientRepository;
import com.nal95.clinic.utils.BMICalculator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
@CrossOrigin
@RequiredArgsConstructor
@Slf4j
public class PatientController {

    private final PatientRepository repository;
    Map<String, String> filtersEntries = new HashMap<>();

    @GetMapping(value = "/patients")
    public List<Patient> getPatients(){
        return repository.findAll();
    }

    @GetMapping(value = "/patients/{id}")
    public Patient getPatient(@PathVariable("id") int id){
        return repository.findById(id).orElseThrow();
    }

    @PostMapping(value = "/patients")
    public Patient savePatients(@RequestBody Patient patient){
        return repository.save(patient);
    }
    @GetMapping(value="/patients/analyze/{id}")
    public Patient analyze(@PathVariable("id") int id){
        Patient patient = repository.findById(id).orElseThrow();
        List<ClinicalData> clinicalData = patient.getClinicalData();
        List<ClinicalData> duplicateClinicalData = new ArrayList<>(clinicalData);
        for(ClinicalData eachEntry:duplicateClinicalData){
            log.info("eachEntry Name " + eachEntry.getComponentName() +" eachEntry Value " + eachEntry.getComponentValue() );
            if(filtersEntries.containsKey(eachEntry.getComponentName())){
                clinicalData.remove(eachEntry);
                continue;
            }else {
                filtersEntries.put(eachEntry.getComponentName(),null);
            }
            log.info("clinicalData: " + clinicalData);
            log.info("filtersEntries: " + filtersEntries);
            log.info("eachEntry: " + eachEntry);
            BMICalculator.calculateBMI(clinicalData,eachEntry);
        }
        filtersEntries.clear();
        return patient;
    }
}
