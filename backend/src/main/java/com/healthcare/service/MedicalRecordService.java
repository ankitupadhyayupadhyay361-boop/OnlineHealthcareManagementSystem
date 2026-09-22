package com.healthcare.service;

import com.healthcare.entity.MedicalRecord;
import com.healthcare.repository.MedicalRecordRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MedicalRecordService {

    private final MedicalRecordRepository medicalRecordRepository;

    public MedicalRecordService(
            MedicalRecordRepository medicalRecordRepository) {

        this.medicalRecordRepository =
                medicalRecordRepository;
    }

    public MedicalRecord saveMedicalRecord(
            MedicalRecord medicalRecord) {

        return medicalRecordRepository.save(
                medicalRecord
        );
    }

    public List<MedicalRecord> getAllMedicalRecords() {

        return medicalRecordRepository.findAll();
    }

    public Optional<MedicalRecord> getMedicalRecordById(
            Long id) {

        return medicalRecordRepository.findById(id);
    }

    public List<MedicalRecord> getRecordsByPatient(
            String patientName) {

        return medicalRecordRepository
                .findByPatientNameContainingIgnoreCase(
                        patientName
                );
    }

    public List<MedicalRecord> getRecordsByDoctor(
            String doctorName) {

        return medicalRecordRepository
                .findByDoctorNameContainingIgnoreCase(
                        doctorName
                );
    }

    public List<MedicalRecord> getRecordsByPatientId(
            Long patientId) {

        return medicalRecordRepository
                .findByPatientId(patientId);
    }

    public List<MedicalRecord> getRecordsByDoctorId(
            Long doctorId) {

        return medicalRecordRepository
                .findByDoctorId(doctorId);
    }

    public void deleteMedicalRecord(Long id) {

        medicalRecordRepository.deleteById(id);
    }
}
