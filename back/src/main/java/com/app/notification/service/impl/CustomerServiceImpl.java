package com.app.notification.service.impl;

import com.app.notification.repository.AddressRepository;
import com.app.notification.dto.customer.AddressDto;
import com.app.notification.dto.customer.CustomerDto;
import com.app.notification.dto.customer.PreferenceDto;
import com.app.notification.entity.customer.Address;
import com.app.notification.entity.customer.Customer;
import com.app.notification.entity.customer.Preference;
import com.app.notification.exceptions.ResourceNotFoundException;
import com.app.notification.mapper.CustomerMapper;
import com.app.notification.repository.CustomerRepository;
import com.app.notification.repository.PreferenceRepository;
import com.app.notification.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;



@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private PreferenceRepository preferenceRepository;

    @Autowired
    private AddressRepository addressRepository;


    @Transactional // Ensures that the entire operation is atomic
    public Boolean createCustomer(CustomerDto customerDto) {

        try {
            // Map and save the Customer entity
            Customer customer = mapCustomerDtoToEntity(customerDto);

            Customer savedCustomer = customerRepository.save(customer);

            // Save Notification Preferences
            saveNotificationPreferences(customerDto, savedCustomer);

            // Save Addresses
            saveAddresses(customerDto, savedCustomer);

            return true;
        } catch (Exception e) {
            return false;
        }
    }


    private Customer mapCustomerDtoToEntity(CustomerDto customerDto) {
        Customer customer = new Customer();
        customer.setName(customerDto.getName());
        customer.setEmail(customerDto.getEmail());
        customer.setPhone(customerDto.getPhone());
        return customer;
    }

    private void saveNotificationPreferences(CustomerDto customerDto, Customer savedCustomer) {
        if (customerDto.getNotificationPreferences() != null) {
            for (PreferenceDto n : customerDto.getNotificationPreferences()) {
                Preference preference = new Preference(
                        savedCustomer, n.getPreferenceType(), n.getOptIn()
                );
                preferenceRepository.save(preference);
            }
        }
    }

    private void saveAddresses(CustomerDto customerDto, Customer savedCustomer) {
        if (customerDto.getAddresses() != null) {
            for (AddressDto addressDto : customerDto.getAddresses()) {
                Address address = new Address(savedCustomer, addressDto.getType(), addressDto.getAddress());
                addressRepository.save(address);
            }
        }
    }

    @Transactional
    public Boolean deleteCustomerById(Long customerId) {
        try {
            // Check if the customer exists
            Customer customer = customerRepository.findById(customerId).get();

            if (customer == null){
                return false;
            }

            // Delete the customer (cascade will handle related entities)
            customerRepository.deleteById(customerId);

            return true;
        } catch (Exception e) {
            // Log the exception for debugging purposes
//            e.printStackTrace();
            return false;
        }
    }

    public List<Customer> findAllCustomer(){
        return customerRepository.findAllCustomersWithDetails();
    }

    public Customer updateUser(CustomerDto customerDto) throws ResourceNotFoundException {
        // Retrieve the existing user by ID
        Customer customer = customerRepository.findById(customerDto.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found with ID " + customerDto.getId()));

        // Update the fields

        customer.setName(customerDto.getName());
        customer.setEmail(customerDto.getName());
        customer.setPhone(customerDto.getPhone());


        // Save the updated user
        return customerRepository.save(customer);
    }


    @Transactional
    public void removeMany(List<Long> ids) {
        // Ensure all customers exist before deletion
        List<Customer> customers = customerRepository.findAllById(ids);

        if (customers.size() != ids.size()) {
            throw new IllegalArgumentException("One or more customers do not exist.");
        }

        // Remove all customers in one transaction
        customerRepository.deleteAll(customers);
    }


    public CustomerDto findById(long customerId) throws ResourceNotFoundException {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found with ID " + customerId));

        return CustomerMapper.mapToCustomerDto(customer);
    }


    public List<CustomerDto> findCustomersByQuery(String query) {
        try {
            List<Customer> customers = customerRepository.findCustomerByQuery(query);
            return customers.stream()
                    .map(CustomerMapper::mapToCustomerDto)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            // Handle the exception (e.g., log it)
            return Collections.emptyList(); // Return an empty list or a fallback value
        }
    }

    public List<CustomerDto> findCustomerByPreferenceType(String type) {
        try {
            List<Customer> customers = customerRepository.findCustomerByPreferenceType(type);
            return customers.stream()
                    .map(CustomerMapper::mapToCustomerDto)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            // Handle the exception (e.g., log it)
            return Collections.emptyList(); // Return an empty list or a fallback value
        }
    }

    @Override
    public CustomerDto findCustomerByEmail(String email) {
        Customer customer = customerRepository.findByEmail(email);
        if(customer != null){
            return CustomerMapper.mapToCustomerDto(customer);
        }
        return null;
    }


}