package com.healthcare.controller;

import com.healthcare.entity.Schedule;
import com.healthcare.service.ScheduleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/schedules")
@CrossOrigin(origins = "*")
public class ScheduleController {

    private final ScheduleService scheduleService;

    public ScheduleController(
            ScheduleService scheduleService) {

        this.scheduleService = scheduleService;
    }

    @PostMapping
    public Schedule createSchedule(
            @RequestBody Schedule schedule) {

        return scheduleService.saveSchedule(schedule);
    }

    @GetMapping
    public List<Schedule> getAllSchedules() {

        return scheduleService.getAllSchedules();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Schedule> getScheduleById(
            @PathVariable Long id) {

        return scheduleService
                .getScheduleById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/doctor-id/{doctorId}")
    public List<Schedule> getSchedulesByDoctorId(
            @PathVariable Long doctorId) {

        return scheduleService
                .getSchedulesByDoctorId(doctorId);
    }

    @GetMapping("/doctor/{doctorName}")
    public List<Schedule> searchSchedulesByDoctorName(
            @PathVariable String doctorName) {

        return scheduleService
                .searchSchedulesByDoctorName(doctorName);
    }

    @GetMapping("/date/{scheduleDate}")
    public List<Schedule> getSchedulesByDate(
            @PathVariable String scheduleDate) {

        return scheduleService
                .getSchedulesByDate(scheduleDate);
    }

    @GetMapping("/availability/{available}")
    public List<Schedule> getSchedulesByAvailability(
            @PathVariable boolean available) {

        return scheduleService
                .getSchedulesByAvailability(available);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Schedule> updateSchedule(
            @PathVariable Long id,
            @RequestBody Schedule schedule) {

        return scheduleService
                .getScheduleById(id)
                .map(existingSchedule -> {

                    existingSchedule.setDoctorId(
                            schedule.getDoctorId());

                    existingSchedule.setDoctorName(
                            schedule.getDoctorName());

                    existingSchedule.setScheduleDate(
                            schedule.getScheduleDate());

                    existingSchedule.setStartTime(
                            schedule.getStartTime());

                    existingSchedule.setEndTime(
                            schedule.getEndTime());

                    existingSchedule.setAvailable(
                            schedule.isAvailable());

                    return ResponseEntity.ok(
                            scheduleService
                                    .saveSchedule(existingSchedule));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteSchedule(
            @PathVariable Long id) {

        if (scheduleService
                .getScheduleById(id)
                .isPresent()) {

            scheduleService.deleteSchedule(id);

            return ResponseEntity.ok(
                    "Schedule deleted successfully");
        }

        return ResponseEntity.notFound().build();
    }
}
