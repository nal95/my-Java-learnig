package com.nal95.clinic.api;

import com.nal95.clinic.dto.request.PatientRequest;
import com.nal95.clinic.dto.request.PatientResponse;
import com.nal95.clinic.model.ClinicalData;
import com.nal95.clinic.model.Patient;
import com.nal95.clinic.repos.PatientRepository;
import com.nal95.clinic.services.PatientService;
import com.nal95.clinic.utils.BMICalculator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:4200")
@Slf4j
public class PatientController {

    private final PatientRepository patientRepository;
    private final PatientService patientService;
    Map<String, String> filtersEntries = new HashMap<>();

    @GetMapping("/patients")
    public List<Patient> getPatients() {
        return patientRepository.findAll();
    }

    @GetMapping("/patient/{id}")
    public Patient getPatient(@PathVariable("id") long id) {
        return patientRepository.findById(id).orElse(null);
    }

    @PostMapping("/patients")
    public PatientResponse savePatient(@RequestBody PatientRequest patient) {
        return patientService.addPatient(patient);
    }

    @GetMapping(value = "/verification/{token}")
    public ResponseEntity<String> verification(@PathVariable String token) {
        patientService.verifyAccount(token);
        return new ResponseEntity<>("Account Activated Successful !!", HttpStatus.OK);
    }


    @GetMapping(value = "/patients/analyze/{id}")
    public Patient analyze(@PathVariable("id") long id) {
        Patient patient = patientRepository.findById(id).orElseThrow();
        List<ClinicalData> clinicalData = patient.getClinicalData();
        List<ClinicalData> duplicateClinicalData = new ArrayList<>(clinicalData);
        for (ClinicalData eachEntry : duplicateClinicalData) {
            log.info("eachEntry Name " + eachEntry.getComponentName() + " eachEntry Value " + eachEntry.getComponentValue());
            if (filtersEntries.containsKey(eachEntry.getComponentName())) {
                clinicalData.remove(eachEntry);
                continue;
            } else {
                filtersEntries.put(eachEntry.getComponentName(), null);
            }
            log.info("clinicalData: " + clinicalData);
            log.info("filtersEntries: " + filtersEntries);
            log.info("eachEntry: " + eachEntry);
            BMICalculator.calculateBMI(clinicalData, eachEntry);
        }
        filtersEntries.clear();
        return patient;
    }
}

