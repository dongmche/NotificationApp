package com.app.notification;


import com.app.notification.controller.auth.AuthenticationController;
import com.app.notification.controller.auth.AuthenticationRequest;
import com.app.notification.dto.UserDto;
import com.app.notification.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Transactional
public class AuthenticationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private UserService userService;

    @InjectMocks
    private AuthenticationController userController;

    @Autowired
    private ObjectMapper objectMapper;  // To convert objects to JSON

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testRegisterUser_Success() throws Exception {
        // Mock successful registration
        UserDto validUserDto = new UserDto();
        validUserDto.setName("John Doe");
        validUserDto.setEmail("johndoe@example.com");
        validUserDto.setPassword("password123");

        when(userService.findUserByEmail(validUserDto.getEmail())).thenReturn(null);  // No user with this email exists
        when(userService.createUser(any(UserDto.class))).thenReturn(true);  // Successfully created

        mockMvc.perform(post("/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(validUserDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", is("User registered successfully")));
    }


    @Test
    public void testRegisterUser_ValidationErrors() throws Exception {
        // Mock validation errors (e.g., missing email)
        UserDto invalidUserDto = new UserDto();
        invalidUserDto.setName("");  // Invalid, empty name
        invalidUserDto.setEmail("");  // Invalid, empty email
        invalidUserDto.setPassword("password123");

        mockMvc.perform(post("/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(invalidUserDto)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.name", is("name can not be empty")))
                .andExpect(jsonPath("$.email", is("email can not be empty")));
    }


}