package com.app.notification;

import com.app.notification.dto.customer.CustomerDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Transactional
public class PreferenceControllerTest {
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

}
