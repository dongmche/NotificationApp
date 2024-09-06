package com.app.notification.controller.auth;

import lombok.Data;

@Data
public class AuthenticationResponse {

    private final String jwt;
    private final String email;

    public AuthenticationResponse(String jwt, String email) {
        this.jwt = jwt;
        this.email = email;
    }

}