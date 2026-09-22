package com.healthcare.controller;

import com.healthcare.entity.MedicalRecord;
import com.healthcare.service.MedicalRecordService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/medical-records")
@CrossOrigin(origins = "*")
public class MedicalRecordController {

    private final MedicalRecordService medicalRecordService;

    public MedicalRecordController(
            MedicalRecordService medicalRecordService) {

        this.medicalRecordService = medicalRecordService;
    }

    @PostMapping
    public MedicalRecord createMedicalRecord(
            @RequestBody MedicalRecord medicalRecord) {

        return medicalRecordService
                .saveMedicalRecord(medicalRecord);
    }

    @GetMapping
    public List<MedicalRecord> getAllMedicalRecords() {

        return medicalRecordService
                .getAllMedicalRecords();
    }

    @GetMapping("/{id}")
    public ResponseEntity<MedicalRecord> getMedicalRecordById(
            @PathVariable Long id) {

        return medicalRecordService
                .getMedicalRecordById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/patient/{patientName}")
    public List<MedicalRecord> getRecordsByPatient(
            @PathVariable String patientName) {

        return medicalRecordService
                .getRecordsByPatient(patientName);
    }

    @GetMapping("/doctor/{doctorName}")
    public List<MedicalRecord> getRecordsByDoctor(
            @PathVariable String doctorName) {

        return medicalRecordService
                .getRecordsByDoctor(doctorName);
    }

    @GetMapping("/patient-id/{patientId}")
    public List<MedicalRecord> getRecordsByPatientId(
            @PathVariable Long patientId) {

        return medicalRecordService
                .getRecordsByPatientId(patientId);
    }

    @GetMapping("/doctor-id/{doctorId}")
    public List<MedicalRecord> getRecordsByDoctorId(
            @PathVariable Long doctorId) {

        return medicalRecordService
                .getRecordsByDoctorId(doctorId);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MedicalRecord> updateMedicalRecord(
            @PathVariable Long id,
            @RequestBody MedicalRecord medicalRecord) {

        return medicalRecordService
                .getMedicalRecordById(id)
                .map(existingRecord -> {

                    if (medicalRecord.getPatientId() != null) {
                        existingRecord.setPatientId(
                                medicalRecord.getPatientId()
                        );
                    }

                    if (medicalRecord.getDoctorId() != null) {
                        existingRecord.setDoctorId(
                                medicalRecord.getDoctorId()
                        );
                    }

                    if (medicalRecord.getPatientName() != null) {
                        existingRecord.setPatientName(
                                medicalRecord.getPatientName()
                        );
                    }

                    if (medicalRecord.getDoctorName() != null) {
                        existingRecord.setDoctorName(
                                medicalRecord.getDoctorName()
                        );
                    }

                    if (medicalRecord.getRecordDate() != null) {
                        existingRecord.setRecordDate(
                                medicalRecord.getRecordDate()
                        );
                    }

                    if (medicalRecord.getDiagnosis() != null) {
                        existingRecord.setDiagnosis(
                                medicalRecord.getDiagnosis()
                        );
                    }

                    if (medicalRecord.getTreatment() != null) {
                        existingRecord.setTreatment(
                                medicalRecord.getTreatment()
                        );
                    }

                    if (medicalRecord.getPrescription() != null) {
                        existingRecord.setPrescription(
                                medicalRecord.getPrescription()
                        );
                    }

                    if (medicalRecord.getNotes() != null) {
                        existingRecord.setNotes(
                                medicalRecord.getNotes()
                        );
                    }

                    return ResponseEntity.ok(
                            medicalRecordService
                                    .saveMedicalRecord(
                                            existingRecord
                                    )
                    );
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteMedicalRecord(
            @PathVariable Long id) {

        if (medicalRecordService
                .getMedicalRecordById(id)
                .isPresent()) {

            medicalRecordService
                    .deleteMedicalRecord(id);

            return ResponseEntity.ok(
                    "Medical record deleted successfully"
            );
        }

        return ResponseEntity.notFound().build();
    }
}
