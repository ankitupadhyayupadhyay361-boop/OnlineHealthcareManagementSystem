package com.healthcare.repository;

import com.healthcare.entity.MedicalRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MedicalRecordRepository
        extends JpaRepository<MedicalRecord, Long> {

    List<MedicalRecord> findByPatientId(Long patientId);

    List<MedicalRecord> findByDoctorId(Long doctorId);

    List<MedicalRecord> findByPatientNameContainingIgnoreCase(
            String patientName);

    List<MedicalRecord> findByDoctorNameContainingIgnoreCase(
            String doctorName);

    List<MedicalRecord> findByDiagnosisContainingIgnoreCase(
            String diagnosis);

    List<MedicalRecord> findByRecordDate(String recordDate);
}
