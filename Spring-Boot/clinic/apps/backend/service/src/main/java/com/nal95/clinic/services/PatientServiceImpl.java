package com.nal95.clinic.services;

import com.nal95.clinic.dto.request.NotificationEmail;
import com.nal95.clinic.dto.request.PatientRequest;
import com.nal95.clinic.model.Patient;
import com.nal95.clinic.model.VerificationToken;
import com.nal95.clinic.repos.PatientRepository;
import com.nal95.clinic.repos.VerificationTokenRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
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
        if ((patientRepository.findUserByEmail(newPatient.getEmail())) != null)
            throw new RuntimeException("DB already have this Email");

        Patient patient = new Patient();
        BeanUtils.copyProperties(newPatient, patient);
        patient.setCreated(Timestamp.from(Instant.now()));
        patient.setEnabled(false);
        patientRepository.save(patient);
        String token = generateVerificationToken(patient);
        String subject = "Account Activation";
        String recipient = patient.getEmail();
        String body = "Welcome " + patient.getFirstName().toUpperCase() + ". " + patient.getLastName().toUpperCase() +
                "\nTo activate your account please ";
        mailService.sendMail(new NotificationEmail(subject, recipient, body), token);
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

    private String generateVerificationToken(Patient patient) {
        String token = UUID.randomUUID().toString();
        VerificationToken verificationToken = new VerificationToken();
        verificationToken.setToken(token);
        Instant instant = Instant.now();
        verificationToken.setExpiryDate(instant.plus(1, ChronoUnit.DAYS));
        verificationToken.setPatient(patient);

        verificationTokenRepository.save(verificationToken);
        return token;
    }
}
