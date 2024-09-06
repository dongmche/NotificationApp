package com.app.notification.controller;

import com.app.notification.service.CustomerService;
import com.app.notification.service.impl.CustomerServiceImpl;
import com.app.notification.dto.customer.CustomerDto;
import com.app.notification.entity.customer.Customer;
import com.app.notification.exceptions.ResourceNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;


    @PostMapping ("/create")
    public ResponseEntity<?> createCustomer(@Valid @RequestBody CustomerDto customerDto,
                                                 BindingResult result) {
        if (result.hasErrors()) {
            Map<String, String> errorMap = new HashMap<>();
            result.getFieldErrors().forEach(error ->
                    errorMap.put(error.getField(), error.getDefaultMessage())
            );
            return ResponseEntity.badRequest().body(errorMap);
        }
        try {
            customerService.createCustomer(customerDto);
            return new ResponseEntity<>("Customer created successfully", HttpStatus.CREATED);
        }catch (Exception e) {
            // Handle other exceptions
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("can not create a customer, values are not unique " + e.getMessage());
        }
    }

    @GetMapping("/email")
    public ResponseEntity<?> getCustomerByEmail(@RequestParam(name = "email") String email) {
        try{
            CustomerDto customer = customerService.findCustomerByEmail(email);
            if(customer != null){
                return ResponseEntity.ok(customer);
            }else{
                return ResponseEntity.noContent().build();
            }
        }catch (Exception e){
            System.out.println("catcjed an error");
            return ResponseEntity.status(500).body("Internal error");
        }

    }


    @GetMapping("/{id}")
    public ResponseEntity<?> getCustomer(@PathVariable("id") long customerId) {
        try {
            CustomerDto customerDto = customerService.findById(customerId);
            if(customerDto != null){
                return ResponseEntity.ok(customerDto);
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Customer with ID " + customerId + " does not exist");
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Customer with ID " + customerId + " does not exist");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(e.getMessage());
        }
    }



    @PostMapping("/edit/{id}")
    public ResponseEntity<?> editCustomer(@Valid @RequestBody CustomerDto customerDto,
                                            BindingResult result) {
        if (customerDto.getId() == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("customer id required ! ");
        }

        if (result.hasErrors()) {
            Map<String, String> errorMap = new HashMap<>();
            result.getFieldErrors().forEach(error ->
                    errorMap.put(error.getField(), error.getDefaultMessage())
            );
            return ResponseEntity.badRequest().body(errorMap);
        }

        try {
            Customer updatedCustomer = customerService.updateUser(customerDto);
            return ResponseEntity.ok(updatedCustomer);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Customer with ID " + customerDto.getId() + " does not exist");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(e.getMessage());
        }
    }


    @DeleteMapping("/delete/{customer_id}")
    public ResponseEntity<?> deleteCustomer(@PathVariable("customer_id") long customerId) {
        boolean deleted = customerService.deleteCustomerById(customerId);

        if (deleted) {
            return ResponseEntity.ok("Customer deleted successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Customer with ID " + customerId + " does not exist");
        }
    }


    @GetMapping("/getall")
    public ResponseEntity<List<Customer>> getAll() {
        List<Customer> customers = customerService.findAllCustomer();

        if (customers.isEmpty()) {
            return ResponseEntity.noContent().build(); // 204 No Content if no customers are found
        }

        return ResponseEntity.ok(customers); // 200 OK with the list of customers
    }

    @PostMapping("/remove-many")
    public ResponseEntity<?> removeManyCustomers(@RequestBody List<Long> ids) {
        try {
            customerService.removeMany(ids);
            return ResponseEntity.ok().body("Customers deleted successfully");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(500).body("An error occurred while deleting customers");
        }
    }

    @GetMapping("/query")
    public ResponseEntity<?> getCustomersByQuery(@RequestParam(name = "query", defaultValue = "") String query) {
        try {
            // Call the service to get the list of customers
            List<CustomerDto> customers = customerService.findCustomersByQuery(query);
            return ResponseEntity.ok(customers);
        } catch (Exception e) {
            // Handle exceptions (e.g., log it)
            return ResponseEntity.status(500).body("An error occurred while fetching the customers");
        }
    }

    @GetMapping("/type")
    public ResponseEntity<?> getCustomersByPreferenceType(@RequestParam(name = "type") String type) {
        System.out.println("here");
        try {
            // Call the service to get the list of customers
            List<CustomerDto> customers = customerService.findCustomerByPreferenceType(type);
            return ResponseEntity.ok(customers);
        } catch (Exception e) {
            // Handle exceptions (e.g., log it)
            return ResponseEntity.status(500).body("An error occurred while fetching the customers");
        }
    }




}
