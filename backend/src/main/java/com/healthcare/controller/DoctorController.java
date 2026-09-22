package com.healthcare.controller;

import com.healthcare.entity.Doctor;
import com.healthcare.service.DoctorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/doctors")
@CrossOrigin(origins = "*")
public class DoctorController {

    private final DoctorService doctorService;

    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @PostMapping
    public Doctor createDoctor(@RequestBody Doctor doctor) {
        return doctorService.saveDoctor(doctor);
    }

    @GetMapping
    public List<Doctor> getAllDoctors() {
        return doctorService.getAllDoctors();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Doctor> getDoctorById(
            @PathVariable Long id) {

        return doctorService
                .getDoctorById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/specialization/{specialization}")
    public List<Doctor> getDoctorsBySpecialization(
            @PathVariable String specialization) {

        return doctorService
                .getDoctorsBySpecialization(specialization);
    }

    @GetMapping("/search/{name}")
    public List<Doctor> searchDoctors(
            @PathVariable String name) {

        return doctorService
                .searchDoctorsByName(name);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Doctor> updateDoctor(
            @PathVariable Long id,
            @RequestBody Doctor doctor) {

        return doctorService
                .getDoctorById(id)
                .map(existingDoctor -> {

                    if (doctor.getName() != null) {
                        existingDoctor.setName(
                                doctor.getName()
                        );
                    }

                    if (doctor.getEmail() != null) {
                        existingDoctor.setEmail(
                                doctor.getEmail()
                        );
                    }

                    if (doctor.getPhone() != null) {
                        existingDoctor.setPhone(
                                doctor.getPhone()
                        );
                    }

                    if (doctor.getSpecialization() != null) {
                        existingDoctor.setSpecialization(
                                doctor.getSpecialization()
                        );
                    }

                    if (doctor.getQualification() != null) {
                        existingDoctor.setQualification(
                                doctor.getQualification()
                        );
                    }

                    if (doctor.getExperience() >= 0) {
                        existingDoctor.setExperience(
                                doctor.getExperience()
                        );
                    }

                    existingDoctor.setAvailable(
                            doctor.isAvailable()
                    );

                    return ResponseEntity.ok(
                            doctorService.saveDoctor(
                                    existingDoctor
                            )
                    );
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteDoctor(
            @PathVariable Long id) {

        if (doctorService
                .getDoctorById(id)
                .isPresent()) {

            doctorService.deleteDoctor(id);

            return ResponseEntity.ok(
                    "Doctor deleted successfully"
            );
        }

        return ResponseEntity.notFound().build();
    }
}
