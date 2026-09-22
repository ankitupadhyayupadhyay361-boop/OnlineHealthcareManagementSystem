package com.healthcare.service;

import com.healthcare.entity.SystemSetting;
import com.healthcare.repository.SystemSettingRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SystemSettingService {

    private final SystemSettingRepository systemSettingRepository;

    public SystemSettingService(
            SystemSettingRepository systemSettingRepository) {

        this.systemSettingRepository = systemSettingRepository;
    }

    public SystemSetting saveSetting(SystemSetting setting) {
        return systemSettingRepository.save(setting);
    }

    public List<SystemSetting> getAllSettings() {
        return systemSettingRepository.findAll();
    }

    public Optional<SystemSetting> getSettingById(Long id) {
        return systemSettingRepository.findById(id);
    }

    public Optional<SystemSetting> getSettingByName(
            String settingName) {

        return systemSettingRepository
                .findBySettingName(settingName);
    }

    public void deleteSetting(Long id) {
        systemSettingRepository.deleteById(id);
    }
}
