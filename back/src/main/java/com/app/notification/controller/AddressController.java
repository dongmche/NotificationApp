package com.app.notification.controller;

import com.app.notification.exceptions.ResourceNotFoundException;
import com.app.notification.service.AddressService;
import com.app.notification.service.impl.AddressServiceImpl;
import com.app.notification.dto.customer.AddressDto;
import com.app.notification.entity.customer.Address;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/addresses")
public class AddressController {

    @Autowired
    private AddressService addressService;



    @PostMapping("/create")
    public ResponseEntity<?> createAddress(@Valid @RequestBody AddressDto addressDto,
                                                 BindingResult result) {
        System.out.println("in a create");
        if (result.hasErrors()) {
            Map<String, String> errorMap = new HashMap<>();
            result.getFieldErrors().forEach(error ->
                    errorMap.put(error.getField(), error.getDefaultMessage())
            );
            return ResponseEntity.badRequest().body(errorMap);
        }

        try {
            Address savedAddress = addressService.addAddress(addressDto);
            return new ResponseEntity<>(savedAddress, HttpStatus.CREATED);
        }catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Customer with ID " + addressDto.getId() + " does not exist");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> removeAddressById(@PathVariable Long id) {
        if(addressService.removeAddressById(id)){
            return new ResponseEntity<>("address with id : " + id + " deleted", HttpStatus.ACCEPTED);
        }
        return new ResponseEntity<>("error address with id : " + id + " do not exists", HttpStatus.BAD_REQUEST);
    }

}
