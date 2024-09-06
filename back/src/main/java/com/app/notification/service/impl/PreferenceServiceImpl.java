package com.app.notification.service.impl;

import com.app.notification.dto.customer.PreferenceDto;
import com.app.notification.entity.customer.Customer;
import com.app.notification.entity.customer.Preference;
import com.app.notification.mapper.PreferenceMapper;
import com.app.notification.repository.CustomerRepository;
import com.app.notification.repository.PreferenceRepository;
import com.app.notification.service.PreferenceService;
import org.hibernate.ResourceClosedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PreferenceServiceImpl implements PreferenceService {
    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private PreferenceRepository preferenceRepository;


    public PreferenceDto addPreference(PreferenceDto preferenceDto) {
        Customer customer = customerRepository.findById(preferenceDto.getCustomer_id())
                .orElseThrow(() -> new ResourceClosedException("Customer not found"));

        Preference preference = new Preference();
        preference.setCustomer(customer);
        preference.setOptIn(preferenceDto.getOptIn());
        preference.setPreferenceType(preferenceDto.getPreferenceType());

        return PreferenceMapper.mapToPreferenceDto(preferenceRepository.save(preference));
    }



    public boolean removePreferenceById(Long preferenceId) {
        if (preferenceRepository.existsById(preferenceId)) {
            preferenceRepository.deleteById(preferenceId);
            return true;
        } else {
            return false;
        }
    }

}




