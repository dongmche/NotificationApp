package com.app.notification.mapper;

import com.app.notification.dto.customer.AddressDto;
import com.app.notification.dto.customer.CustomerDto;
import com.app.notification.dto.customer.PreferenceDto;
import com.app.notification.entity.customer.Customer;

import java.util.Set;
import java.util.stream.Collectors;

public class CustomerMapper {
    public static CustomerDto mapToCustomerDto(Customer customer) {
            if (customer == null) {
                return null;
            }


            CustomerDto dto = new CustomerDto();
            dto.setId(customer.getId());
            dto.setName(customer.getName());
            dto.setEmail(customer.getEmail());
            dto.setPhone(customer.getPhone());

            if (customer.getVersion() != null ){
                dto.setVersion(customer.getVersion());
            }


            // Map addresses
            if(customer.getAddresses() != null) {
                Set<AddressDto> addressDtos = customer.getAddresses().stream()
                        .map(AddressMapper::mapToAddressDto)
                        .collect(Collectors.toSet());
                dto.setAddresses(addressDtos);
            }


            // Map preferences
            if(customer.getPreferences() != null) {
                Set<PreferenceDto> preferenceDtos = customer.getPreferences().stream()
                        .map(PreferenceMapper::mapToPreferenceDto)  // Use the class name and method reference
                        .collect(Collectors.toSet());
                dto.setNotificationPreferences(preferenceDtos);
            }

            return dto;
    }


}
