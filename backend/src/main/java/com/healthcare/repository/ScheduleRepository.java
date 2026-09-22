package com.healthcare.repository;

import com.healthcare.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScheduleRepository
        extends JpaRepository<Schedule, Long> {

    List<Schedule> findByDoctorId(Long doctorId);

    List<Schedule> findByDoctorNameContainingIgnoreCase(
            String doctorName);

    List<Schedule> findByScheduleDate(String scheduleDate);

    List<Schedule> findByAvailable(boolean available);
}
