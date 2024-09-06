package com.app.notification.dto.customer;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressDto {
    private Long id;
    @NotNull(message = "customer_id can not be null")
    private Long customerId;
    @NotEmpty(message = "type can not be empty")
    private String type;
    @NotEmpty(message = "address can not be empty")
    private String address;

    public AddressDto(String type, String address) {
        this.type = type;
        this.address = address;
    }
}