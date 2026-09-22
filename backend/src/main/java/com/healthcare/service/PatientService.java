package com.healthcare.service;

import com.healthcare.entity.Patient;
import com.healthcare.repository.PatientRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class PatientService {

    private final PatientRepository patientRepository;

    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public Patient savePatient(Patient patient) {
        return patientRepository.save(Objects.requireNonNull(patient, "patient must not be null"));
    }

    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    public Optional<Patient> getPatientById(Long id) {
        return patientRepository.findById(Objects.requireNonNull(id, "id must not be null"));
    }

    public List<Patient> searchPatientsByName(String name) {
        return patientRepository.findByNameContainingIgnoreCase(name);
    }

    public List<Patient> getPatientsByBloodGroup(String bloodGroup) {
        return patientRepository.findByBloodGroup(bloodGroup);
    }

    public List<Patient> getPatientsByGender(String gender) {
        return patientRepository.findByGender(gender);
    }

    public void deletePatient(Long id) {
        patientRepository.deleteById(Objects.requireNonNull(id, "id must not be null"));
    }
}
