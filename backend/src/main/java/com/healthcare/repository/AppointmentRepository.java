package com.healthcare.repository;

import com.healthcare.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    List<Appointment> findByPatientName(String patientName);

    List<Appointment> findByDoctorNameContainingIgnoreCase(String doctorName);

    List<Appointment> findByStatus(String status);

    List<Appointment> findByAppointmentDate(String appointmentDate);
}
