package com.healthcare.service;

import com.healthcare.entity.Doctor;
import com.healthcare.repository.DoctorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class DoctorService {

    private final DoctorRepository doctorRepository;

    // Constructor Injection
    public DoctorService(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    // Create or Update Doctor
    public Doctor saveDoctor(Doctor doctor) {
        return doctorRepository.save(Objects.requireNonNull(doctor, "doctor must not be null"));
    }

    // Get All Doctors
    public List<Doctor> getAllDoctors() {
        return doctorRepository.findAll();
    }

    // Get Doctor By ID
    public Optional<Doctor> getDoctorById(Long id) {
        return doctorRepository.findById(Objects.requireNonNull(id, "id must not be null"));
    }

    // Find Doctors By Specialization
    public List<Doctor> getDoctorsBySpecialization(String specialization) {
        return doctorRepository.findBySpecialization(specialization);
    }

    // Search Doctors By Name
    public List<Doctor> searchDoctorsByName(String name) {
        return doctorRepository.findByNameContainingIgnoreCase(name);
    }

    // Delete Doctor
    public void deleteDoctor(Long id) {
        doctorRepository.deleteById(Objects.requireNonNull(id, "id must not be null"));
    }
}