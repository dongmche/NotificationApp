package com.app.notification.service;

import com.app.notification.dto.customer.AddressDto;
import com.app.notification.entity.customer.Address;
import com.app.notification.entity.customer.Customer;
import org.hibernate.ResourceClosedException;

public interface AddressService {
    public Address addAddress(AddressDto addressDto);

    public boolean removeAddressById(Long addressId);
}
