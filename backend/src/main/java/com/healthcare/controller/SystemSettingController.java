package com.healthcare.controller;

import com.healthcare.entity.SystemSetting;
import com.healthcare.service.SystemSettingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/settings")
@CrossOrigin(origins = "*")
public class SystemSettingController {

    private final SystemSettingService systemSettingService;

    public SystemSettingController(
            SystemSettingService systemSettingService) {

        this.systemSettingService = systemSettingService;
    }

    @PostMapping
    public SystemSetting createSetting(
            @RequestBody SystemSetting setting) {

        return systemSettingService.saveSetting(setting);
    }

    @GetMapping
    public List<SystemSetting> getAllSettings() {

        return systemSettingService.getAllSettings();
    }

    @GetMapping("/{id}")
    public ResponseEntity<SystemSetting> getSettingById(
            @PathVariable Long id) {

        return systemSettingService
                .getSettingById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/name/{settingName}")
    public ResponseEntity<SystemSetting> getSettingByName(
            @PathVariable String settingName) {

        return systemSettingService
                .getSettingByName(settingName)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<SystemSetting> updateSetting(
            @PathVariable Long id,
            @RequestBody SystemSetting setting) {

        return systemSettingService
                .getSettingById(id)
                .map(existingSetting -> {

                    existingSetting.setSettingName(
                            setting.getSettingName());

                    existingSetting.setSettingValue(
                            setting.getSettingValue());

                    existingSetting.setDescription(
                            setting.getDescription());

                    return ResponseEntity.ok(
                            systemSettingService
                                    .saveSetting(existingSetting));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteSetting(
            @PathVariable Long id) {

        if (systemSettingService
                .getSettingById(id)
                .isPresent()) {

            systemSettingService.deleteSetting(id);

            return ResponseEntity.ok(
                    "System setting deleted successfully");
        }

        return ResponseEntity.notFound().build();
    }
}
