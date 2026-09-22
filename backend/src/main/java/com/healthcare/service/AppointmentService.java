package com.healthcare.service;

import com.healthcare.entity.Appointment;
import com.healthcare.repository.AppointmentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;

    public AppointmentService(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    public Appointment saveAppointment(Appointment appointment) {
        return appointmentRepository.save(Objects.requireNonNull(appointment, "appointment must not be null"));
    }

    public List<Appointment> getAllAppointments() {
        return appointmentRepository.findAll();
    }

    public Optional<Appointment> getAppointmentById(Long id) {
        return appointmentRepository.findById(Objects.requireNonNull(id, "id must not be null"));
    }

    public List<Appointment> getAppointmentsByPatient(String patientName) {
        return appointmentRepository.findByPatientName(patientName);
    }

    public List<Appointment> getAppointmentsByDoctor(String doctorName) {
        return appointmentRepository.findByDoctorNameContainingIgnoreCase(doctorName);
    }

    public List<Appointment> getAppointmentsByStatus(String status) {
        return appointmentRepository.findByStatus(status);
    }

    public List<Appointment> getAppointmentsByDate(String date) {
        return appointmentRepository.findByAppointmentDate(date);
    }

    public void deleteAppointment(Long id) {
        appointmentRepository.deleteById(Objects.requireNonNull(id, "id must not be null"));
    }
}
