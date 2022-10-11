package com.nal95.clinic.api;

import com.nal95.clinic.model.Patient;
import com.nal95.clinic.repos.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class PatientController {

    private final PatientRepository repository;

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
}
