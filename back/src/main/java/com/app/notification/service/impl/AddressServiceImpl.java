package com.app.notification.service.impl;

import com.app.notification.repository.AddressRepository;
import com.app.notification.dto.customer.AddressDto;
import com.app.notification.entity.customer.Address;
import com.app.notification.entity.customer.Customer;
import com.app.notification.repository.CustomerRepository;
import com.app.notification.service.AddressService;
import org.hibernate.ResourceClosedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressServiceImpl implements AddressService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private AddressRepository addressRepository;

    public Address addAddress(AddressDto addressDto) {
        Customer customer = customerRepository.findById(addressDto.getCustomerId())
                .orElseThrow(() -> new ResourceClosedException("Customer not found"));

        Address address = new Address();
        address.setAddress(addressDto.getAddress());
        address.setType(addressDto.getType());
        address.setCustomer(customer);

        return addressRepository.save(address);
    }

    public boolean removeAddressById(Long addressId) {
        if (addressRepository.existsById(addressId)) {
            addressRepository.deleteById(addressId);
            return true;
        } else {
            return false;
        }
    }


}