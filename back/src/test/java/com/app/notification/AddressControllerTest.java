package com.app.notification;

import com.app.notification.dto.customer.AddressDto;
import com.app.notification.dto.customer.CustomerDto;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.hamcrest.Matchers.is;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")  // Activate the "test" profile for test-specific configuration
@Transactional  // Ensure each test runs in a transaction and rolls back after execution
public class AddressControllerTest {

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
        MvcResult result = mockMvc.perform(post("/customers/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(validCustomer)))
                .andExpect(status().isCreated())  // Verify the status is CREATED (201)
                .andExpect(jsonPath("$", is("Customer created successfully")))  // Verify success message
                .andReturn();  // Retrieve the MvcResult object

        String email = "johndoe@example.com";

        // Perform the GET request, passing the email as a query parameter
        result = mockMvc.perform(get("/customers/email")
                        .param("email", email)  // Add the email query parameter
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();  // Capture the result

        String responseBody = result.getResponse().getContentAsString();

        // Create an ObjectMapper instance
        ObjectMapper objectMapper = new ObjectMapper();

        // Parse the response body into a Map
        Map<String, Object> responseMap = objectMapper.readValue(responseBody, new TypeReference<Map<String, Object>>() {
        });

        // Extract the id from the Map
        Long customerId = Long.parseLong(responseMap.get("id").toString());
        System.out.println(customerId);

        // Create an AddressDto object with the extracted customerId
        AddressDto addressDto = new AddressDto();
        addressDto.setCustomerId(customerId);
        addressDto.setType("Home");
        addressDto.setAddress("123 Main St");

        // Perform the POST request to create the address
        MvcResult postResult = mockMvc.perform(post("/addresses/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(addressDto)))
                .andExpect(status().isCreated()) // Expect status 201 Created or modify based on your endpoint
                .andReturn();

        // Extract and verify the response (if needed)
        String postResponseBody = postResult.getResponse().getContentAsString();
        responseMap = objectMapper.readValue(postResponseBody, new TypeReference<Map<String, Object>>() {
        });
        Long addressId = Long.parseLong(responseMap.get("id").toString());

        // Use the extracted ID in the DELETE request
        mockMvc.perform(delete("/addresses/{id}", addressId));
//                .andExpect(status().isNoContent()); // Expect status 204 No Content if deletion is successful


    }


}
