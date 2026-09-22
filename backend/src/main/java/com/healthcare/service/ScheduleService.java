package com.healthcare.service;

import com.healthcare.entity.Schedule;
import com.healthcare.repository.ScheduleRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;

    public ScheduleService(
            ScheduleRepository scheduleRepository) {

        this.scheduleRepository = scheduleRepository;
    }

    public Schedule saveSchedule(Schedule schedule) {

        return scheduleRepository.save(schedule);
    }

    public List<Schedule> getAllSchedules() {

        return scheduleRepository.findAll();
    }

    public Optional<Schedule> getScheduleById(Long id) {

        return scheduleRepository.findById(id);
    }

    public List<Schedule> getSchedulesByDoctorId(
            Long doctorId) {

        return scheduleRepository.findByDoctorId(doctorId);
    }

    public List<Schedule> searchSchedulesByDoctorName(
            String doctorName) {

        return scheduleRepository
                .findByDoctorNameContainingIgnoreCase(doctorName);
    }

    public List<Schedule> getSchedulesByDate(
            String scheduleDate) {

        return scheduleRepository
                .findByScheduleDate(scheduleDate);
    }

    public List<Schedule> getSchedulesByAvailability(
            boolean available) {

        return scheduleRepository
                .findByAvailable(available);
    }

    public void deleteSchedule(Long id) {

        scheduleRepository.deleteById(id);
    }
}
