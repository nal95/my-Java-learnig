package com.nal95.clinic.api;

import com.nal95.clinic.model.ClinicalData;
import com.nal95.clinic.model.Patient;
import com.nal95.clinic.repos.PatientRepository;
import com.nal95.clinic.util.BMICalculator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class PatientController {

    private final PatientRepository repository;
    Map<String, String> filtersEntries = new HashMap<>();

    @Autowired
    PatientController(PatientRepository repository){
        this.repository = repository;
    }

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
            if(filtersEntries.containsKey(eachEntry.getComponentName())){
                clinicalData.remove(eachEntry);
                continue;
            }else {
                filtersEntries.put(eachEntry.getComponentName(),null);
            }
            BMICalculator.calculateBMI(clinicalData,eachEntry);
        }
        filtersEntries.clear();
        return patient;
    }
}
