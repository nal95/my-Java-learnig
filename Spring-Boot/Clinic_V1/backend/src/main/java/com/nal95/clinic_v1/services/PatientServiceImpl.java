package com.nal95.clinic_v1.services;

import com.nal95.clinic_v1.dto.request.NotificationEmail;
import com.nal95.clinic_v1.dto.request.PatientRequest;
import com.nal95.clinic_v1.model.Patient;
import com.nal95.clinic_v1.model.VerificationToken;
import com.nal95.clinic_v1.repos.PatientRepository;
import com.nal95.clinic_v1.repos.VerificationTokenRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class PatientServiceImpl implements PatientService {

    private final PatientRepository patientRepository;
    private final VerificationTokenRepository verificationTokenRepository;
    private final MailService mailService;



    @Override
    public boolean addPatient(PatientRequest newPatient) {
        if((patientRepository.findUserByEmail(newPatient.getEmail())) != null)
            throw new RuntimeException("DB already have this Email");

        Patient patient = new Patient();
        BeanUtils.copyProperties(patient,newPatient);
        patient.setCreated(Timestamp.from(Instant.now()));
        patient.setEnabled(false);
        String token = generateVerificationToken(patient);
        String subject = "Account Activation";
        String recipient = patient.getEmail();
        String body = "Welcome " + patient.getFirstName().toUpperCase() + ". " + patient.getLastName().toUpperCase()+
                "\nTo activate your account please ";
        mailService.sendMail(new NotificationEmail(subject,recipient,body),token);
        return true;
    }

    @Override
    public void verifyAccount(String token) {
        Optional<VerificationToken> vToken = verificationTokenRepository.findByToken(token);
        if (vToken.isEmpty()) {
            throw new RuntimeException("Invalide Token");
        }
        VerificationToken verificationToken = vToken.get();

        fetchPatientAndEnable(verificationToken);
    }

    private void fetchPatientAndEnable(VerificationToken verificationToken) {
        String email = verificationToken.getPatient().getEmail();
        Patient patient = patientRepository.findUserByEmail(email);
        patient.setEnabled(true);
        patientRepository.save(patient);
    }

    private String generateVerificationToken(Patient patient ){
        String token = UUID.randomUUID().toString();
        VerificationToken verificationToken = new VerificationToken();
        verificationToken.setToken(token);
        verificationToken.setPatient(patient);

        verificationTokenRepository.save(verificationToken);
        return token;
    }
}
