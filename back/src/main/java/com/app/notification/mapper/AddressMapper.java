package com.app.notification.mapper;

import com.app.notification.dto.customer.AddressDto;
import com.app.notification.entity.customer.Address;

public class AddressMapper {
    public static AddressDto mapToAddressDto(Address address){
        return new AddressDto(address.getId(), address.getCustomer().getId(), address.getType(), address.getAddress());
    }
}
