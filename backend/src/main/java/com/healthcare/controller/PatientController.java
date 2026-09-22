package com.healthcare.controller;

import com.healthcare.entity.Patient;
import com.healthcare.service.PatientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/patients")
@CrossOrigin(origins = "*")
public class PatientController {

    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @PostMapping
    public Patient createPatient(@RequestBody Patient patient) {
        return patientService.savePatient(patient);
    }

    @GetMapping
    public List<Patient> getAllPatients() {
        return patientService.getAllPatients();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Patient> getPatientById(@PathVariable Long id) {
        return patientService.getPatientById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/search/{name}")
    public List<Patient> searchPatients(@PathVariable String name) {
        return patientService.searchPatientsByName(name);
    }

    @GetMapping("/blood-group/{bloodGroup}")
    public List<Patient> getPatientsByBloodGroup(@PathVariable String bloodGroup) {
        return patientService.getPatientsByBloodGroup(bloodGroup);
    }

    @GetMapping("/gender/{gender}")
    public List<Patient> getPatientsByGender(@PathVariable String gender) {
        return patientService.getPatientsByGender(gender);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Patient> updatePatient(
            @PathVariable Long id,
            @RequestBody Patient patient) {

        return patientService.getPatientById(id)
                .map(existingPatient -> {

                    existingPatient.setName(patient.getName());
                    existingPatient.setEmail(patient.getEmail());
                    existingPatient.setPhone(patient.getPhone());
                    existingPatient.setDateOfBirth(patient.getDateOfBirth());
                    existingPatient.setGender(patient.getGender());
                    existingPatient.setAddress(patient.getAddress());
                    existingPatient.setBloodGroup(patient.getBloodGroup());

                    return ResponseEntity.ok(
                            patientService.savePatient(existingPatient)
                    );
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePatient(@PathVariable Long id) {

        if (patientService.getPatientById(id).isPresent()) {
            patientService.deletePatient(id);
            return ResponseEntity.ok("Patient deleted successfully");
        }

        return ResponseEntity.notFound().build();
    }
}
