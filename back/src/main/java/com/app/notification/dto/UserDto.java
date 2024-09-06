package com.app.notification.dto;



import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserDto {
    private Long id;

//    @NotEmpty(message = "firstName can not be empty")
//    private String firstName;
//
//    @NotEmpty(message = "lastName can not be empty")
//    private String lastName;

    @NotEmpty(message = "name can not be empty")
    private String name;

    @NotEmpty(message = "email can not be empty")
    @Email
    private String email;

    @NotEmpty(message = "password can not be empty")
    private String password;

}
