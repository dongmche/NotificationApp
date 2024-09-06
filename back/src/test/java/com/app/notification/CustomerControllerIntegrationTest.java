package com.app.notification;
import com.app.notification.dto.customer.CustomerDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.is;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")  // Activate the "test" profile for test-specific configuration
@Transactional  // Ensure each test runs in a transaction and rolls back after execution
public class CustomerControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;  // To serialize and deserialize JSON

    @Test
    public void testCreateCustomer_Success() throws Exception {
        // Create a mock CustomerDto with valid data
        CustomerDto validCustomer = new CustomerDto();
        validCustomer.setName("John Doe");
        validCustomer.setEmail("johndoe@example.com");
        validCustomer.setPhone("123456789");

        // Perform the POST request and expect success (HTTP 201)
        mockMvc.perform(post("/customers/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(validCustomer)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$", is("Customer created successfully")));  // Verify success message


    }

    @Test
    public void testCreateCustomer_ValidationError() throws Exception {
        // Create a mock CustomerDto with validation errors (empty name)
        CustomerDto invalidCustomer = new CustomerDto();
        invalidCustomer.setName("");  // Invalid name
        invalidCustomer.setEmail("johndoe@example.com");
        invalidCustomer.setPhone("123456789");

        // Perform the POST request and expect a failure (HTTP 400)
        mockMvc.perform(post("/customers/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(invalidCustomer)))
                .andExpect(status().isBadRequest());
    }


}
