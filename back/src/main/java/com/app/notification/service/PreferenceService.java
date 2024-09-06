package com.app.notification.service;

import com.app.notification.dto.customer.PreferenceDto;

public interface PreferenceService {
    public PreferenceDto addPreference(PreferenceDto preferenceDto);

    public boolean removePreferenceById(Long preferenceId);
}
