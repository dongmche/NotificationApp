package com.app.notification.dto.customer;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
//@EqualsAndHashCode(of = {"preferenceType", "optIn"})
@NoArgsConstructor
@AllArgsConstructor
public class PreferenceDto {
    private Long id;

    @NotNull(message = "customer_id can not be null")
    private Long customer_id;

    @NotEmpty(message = "preference type can not be empty")
    private String preferenceType;

    @NotNull(message = "opt in can not be empty")
    private Boolean optIn;

    // Getters and Setters

    public PreferenceDto(String preferenceType, Boolean optIn) {
        this.preferenceType = preferenceType;
        this.optIn = optIn;
    }
}