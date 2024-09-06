package com.app.notification.service;

import com.app.notification.dto.customer.AddressDto;
import com.app.notification.dto.customer.CustomerDto;
import com.app.notification.dto.customer.PreferenceDto;
import com.app.notification.entity.customer.Address;
import com.app.notification.entity.customer.Customer;
import com.app.notification.entity.customer.Preference;
import com.app.notification.exceptions.ResourceNotFoundException;
import com.app.notification.mapper.CustomerMapper;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public interface CustomerService {
    Boolean createCustomer(CustomerDto customerDto);
    Boolean deleteCustomerById(Long customerId);
    List<Customer> findAllCustomer();
    Customer updateUser(CustomerDto customerDto) throws ResourceNotFoundException;
    void removeMany(List<Long> ids);
    CustomerDto findById(long customerId) throws ResourceNotFoundException;
    List<CustomerDto> findCustomersByQuery(String query);
    List<CustomerDto> findCustomerByPreferenceType(String type);
    CustomerDto findCustomerByEmail(String email);
}