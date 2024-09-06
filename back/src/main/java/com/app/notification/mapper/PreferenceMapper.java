package com.app.notification.mapper;

import com.app.notification.dto.customer.PreferenceDto;
import com.app.notification.entity.customer.Preference;

public class PreferenceMapper {


    public static PreferenceDto mapToPreferenceDto(Preference preference){
        return new PreferenceDto(preference.getId(), preference.getCustomer().getId(),
                    preference.getPreferenceType(), preference.getOptIn());
    }
}
