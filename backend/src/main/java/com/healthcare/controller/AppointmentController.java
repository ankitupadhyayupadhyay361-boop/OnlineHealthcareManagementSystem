package com.healthcare.controller;

import com.healthcare.entity.Appointment;
import com.healthcare.service.AppointmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/appointments")
@CrossOrigin(origins = "*")
public class AppointmentController {

    private final AppointmentService appointmentService;

    public AppointmentController(
            AppointmentService appointmentService) {

        this.appointmentService = appointmentService;
    }

    @PostMapping
    public Appointment createAppointment(
            @RequestBody Appointment appointment) {

        return appointmentService.saveAppointment(appointment);
    }

    @GetMapping
    public List<Appointment> getAllAppointments() {

        return appointmentService.getAllAppointments();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Appointment> getAppointmentById(
            @PathVariable Long id) {

        return appointmentService
                .getAppointmentById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/patient/{patientName}")
    public List<Appointment> getAppointmentsByPatient(
            @PathVariable String patientName) {

        return appointmentService
                .getAppointmentsByPatient(patientName);
    }

    @GetMapping("/doctor/{doctorName}")
    public List<Appointment> getAppointmentsByDoctor(
            @PathVariable String doctorName) {

        return appointmentService
                .getAppointmentsByDoctor(doctorName);
    }

    @GetMapping("/status/{status}")
    public List<Appointment> getAppointmentsByStatus(
            @PathVariable String status) {

        return appointmentService
                .getAppointmentsByStatus(status);
    }

    @GetMapping("/date/{date}")
    public List<Appointment> getAppointmentsByDate(
            @PathVariable String date) {

        return appointmentService
                .getAppointmentsByDate(date);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Appointment> updateAppointment(
            @PathVariable Long id,
            @RequestBody Appointment appointment) {

        return appointmentService
                .getAppointmentById(id)
                .map(existingAppointment -> {

                    /*
                     * Update only fields that are actually
                     * provided in the request.
                     *
                     * This prevents Confirm / Reject /
                     * Complete operations from deleting
                     * existing appointment details.
                     */

                    if (appointment.getDoctorId() != null) {
                        existingAppointment.setDoctorId(
                                appointment.getDoctorId()
                        );
                    }

                    if (appointment.getPatientId() != null) {
                        existingAppointment.setPatientId(
                                appointment.getPatientId()
                        );
                    }

                    if (appointment.getPatientName() != null) {
                        existingAppointment.setPatientName(
                                appointment.getPatientName()
                        );
                    }

                    if (appointment.getDoctorName() != null) {
                        existingAppointment.setDoctorName(
                                appointment.getDoctorName()
                        );
                    }

                    if (appointment.getAppointmentDate() != null) {
                        existingAppointment.setAppointmentDate(
                                appointment.getAppointmentDate()
                        );
                    }

                    if (appointment.getAppointmentTime() != null) {
                        existingAppointment.setAppointmentTime(
                                appointment.getAppointmentTime()
                        );
                    }

                    if (appointment.getAppointmentType() != null) {
                        existingAppointment.setAppointmentType(
                                appointment.getAppointmentType()
                        );
                    }

                    if (appointment.getReason() != null) {
                        existingAppointment.setReason(
                                appointment.getReason()
                        );
                    }

                    if (appointment.getStatus() != null) {
                        existingAppointment.setStatus(
                                appointment.getStatus()
                        );
                    }

                    return ResponseEntity.ok(
                            appointmentService
                                    .saveAppointment(
                                            existingAppointment
                                    )
                    );
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAppointment(
            @PathVariable Long id) {

        if (appointmentService
                .getAppointmentById(id)
                .isPresent()) {

            appointmentService.deleteAppointment(id);

            return ResponseEntity.ok(
                    "Appointment deleted successfully"
            );
        }

        return ResponseEntity.notFound().build();
    }
}
