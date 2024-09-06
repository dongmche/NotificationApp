package com.app.notification.controller;


import com.app.notification.service.PreferenceService;
import com.app.notification.service.impl.PreferenceServiceImpl;
import com.app.notification.dto.customer.PreferenceDto;
import com.app.notification.exceptions.ResourceNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/preferences")
public class PreferenceController {
    @Autowired
    private PreferenceService preferenceService;

    @PostMapping("/create")
    public ResponseEntity<?> createAddress(@Valid @RequestBody PreferenceDto preferenceDto,
                                           BindingResult result) {
        System.out.println("in here");
        if (result.hasErrors()) {
            Map<String, String> errorMap = new HashMap<>();
            result.getFieldErrors().forEach(error ->
                    errorMap.put(error.getField(), error.getDefaultMessage())
            );
            return ResponseEntity.badRequest().body(errorMap);
        }

        try {
            PreferenceDto savedAddress = preferenceService.addPreference(preferenceDto);
            return new ResponseEntity<>(savedAddress, HttpStatus.CREATED);
        }catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Customer with ID " + preferenceDto.getId() + " does not exist");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(e.getMessage());
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> removeAddressById(@PathVariable Long id) {
        if(preferenceService.removePreferenceById(id)){
            return new ResponseEntity<>("preference with id : " + id + " deleted", HttpStatus.ACCEPTED);
        }
        return new ResponseEntity<>("error preference with id : " + id + " do not exists", HttpStatus.BAD_REQUEST);
    }
}
