package com.app.notification.dto.customer;


import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Data
//@EqualsAndHashCode(of = {"name", "email", "phone"})
public class CustomerDto {

    private Long id;

    @NotEmpty(message = "name can not be empty")
    private String name;

    @NotEmpty(message = "email can not be empty")
    private String email;

    @NotEmpty(message = "phone can not be empty")
    private String phone;

    private Integer version;


    private Set<AddressDto> addresses;
    private Set<PreferenceDto> notificationPreferences;


    public CustomerDto(String name, String email, String phone) {
        this.name = name;
        this.email = email;
        this.phone = phone;
    }
}
