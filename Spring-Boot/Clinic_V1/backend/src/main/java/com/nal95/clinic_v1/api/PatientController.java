package com.nal95.clinic_v1.api;

import com.nal95.clinic_v1.dto.request.PatientRequest;
import com.nal95.clinic_v1.model.Patient;
import com.nal95.clinic_v1.repos.PatientRepository;
import com.nal95.clinic_v1.services.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class PatientController {

    private final PatientRepository patientRepository;
    private final PatientService patientService;

    @GetMapping("/patients")
    public List<Patient> getPatients(){
        return patientRepository.findAll();
    }

    @GetMapping("/patient/{id}")
    public Patient getPatient(@PathVariable("id") long id){
        return patientRepository.findById(id).orElse(null);
    }

    @PostMapping("/patients")
    public ResponseEntity<String> savePatient(PatientRequest patient){
        boolean result = patientService.addPatient(patient);
        String returnValue = "";

        if (result){
            returnValue = "Account Activated Successful :) !!";
        }else {
            returnValue = "Something append in the activation process :( !!";
        }
        return new ResponseEntity<>(returnValue, HttpStatus.OK);
    }
}
