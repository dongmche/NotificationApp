package com.app.notification.controller.auth;

import com.app.notification.config.JwtUtil;
import com.app.notification.dto.UserDto;
import com.app.notification.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;


@RestController
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserService userService;

    @Autowired
    private UserDetailsService userDetailsService;


    // autheticate returns a jwt token for client, without it he can not do any authorization requests

    @PostMapping("/authenticate")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequest.getEmail(), authenticationRequest.getPassword())
            );
        } catch (BadCredentialsException e) {
            Map<String, String> errorMap = new HashMap<>();
            errorMap.put("error", "Incorrect username or password");

            // Return the map as the response body with a 400 status
            return ResponseEntity.badRequest().body(errorMap);
        }

        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getEmail());
        final String jwt = jwtUtil.generateToken(authenticationRequest.getEmail());

        return ResponseEntity.ok(new AuthenticationResponse(jwt, userDetails.getUsername()));
    }


    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody UserDto userDto,
                                          BindingResult result) {
        if (result.hasErrors()) {
            Map<String, String> errorMap = new HashMap<>();
            result.getFieldErrors().forEach(error ->
                    errorMap.put(error.getField(), error.getDefaultMessage())
            );
            return ResponseEntity.badRequest().body(errorMap);
        }

        UserDto existingUser = userService.findUserByEmail(userDto.getEmail());

        if (existingUser != null) {
            // Create a map to hold the error message for the email field
            Map<String, String> errorMap = new HashMap<>();
            errorMap.put("email", "Email already used");

            // Return the map as the response body with a 400 status
            return ResponseEntity.badRequest().body(errorMap);
        }


        if (userService.createUser(userDto)) {
            return ResponseEntity.ok("User registered successfully");
        }

        return ResponseEntity.badRequest().body("User registration failed due to an unknown error.");
    }


}

